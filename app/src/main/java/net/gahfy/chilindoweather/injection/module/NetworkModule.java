package net.gahfy.chilindoweather.injection.module;

import android.support.annotation.NonNull;

import net.gahfy.chilindoweather.BuildConfig;
import net.gahfy.chilindoweather.network.OpenWeatherMapApi;
import net.gahfy.chilindoweather.utils.Logger;
import net.gahfy.chilindoweather.utils.Schedulers;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static net.gahfy.chilindoweather.utils.ApiConstants.BASE_URL;
import static net.gahfy.chilindoweather.utils.ApiConstants.QUERY_API_KEY;

/**
 * Module which provides all required dependencies about network
 */
@Module
public class NetworkModule {
    /**
     * Instance of NetworkModule (as it is a Singleton)
     */
    @NonNull
    private static final NetworkModule instance = new NetworkModule();

    /**
     * Instantiates a new NetworkModule. It is here just to make it private and use NetworkModule
     * only as a Singleton.
     */
    private NetworkModule() {
    }

    /**
     * Returns the instance of NetworkModule.
     *
     * @return the instance of NetworkModule
     */
    @NonNull
    public static NetworkModule getInstance() {
        return instance;
    }

    /**
     * Provides the OpenWeatherMap service implementation.
     *
     * @param retrofit the Retrofit instance used to instantiate the service
     * @return the OpenWeatherMap service implementation
     */
    @Provides
    @Reusable
    @NonNull
    static OpenWeatherMapApi provideOpenWeatherMapApi(@NonNull Retrofit retrofit) {
        return retrofit.create(OpenWeatherMapApi.class);
    }

    /**
     * Provides the Retrofit instance.
     *
     * @param okHttpClient The HTTP client
     * @return the Retrofit instance
     */
    @Provides
    @Reusable
    @NonNull
    static Retrofit provideRetrofitInstance(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    /**
     * Provides the HTTP client for Retrofit.
     *
     * @param interceptor the interceptor which is added to the client in order to put the API key.
     * @return the HTTP client for Retrofit
     */
    @Provides
    @Reusable
    @NonNull
    static OkHttpClient provideClient(@Named("get_param_injector") Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    /**
     * Provides the Interceptor for HTTP client, which adds the API key property to each request.
     * @return the Interceptor for HTTP client, which adds the API key property to each request
     */
    @Provides
    @Reusable
    @Named("get_param_injector")
    @NonNull
    static Interceptor provideGetParamInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter(QUERY_API_KEY, BuildConfig.OPENWEATHERMAP_API_KEY)
                        .build();

                Logger.v("Retrofit", "Requesting ".concat(url.toString()));
                if (original.headers().size() > 0) {
                    Logger.v("Retrofit", "---------- Request Headers ----------");
                    for (String headerName : original.headers().names()) {
                        Logger.v("Retrofit", headerName + ": " + original.headers().get(headerName));
                    }
                    Logger.v("Retrofit", "---------- Request Headers ----------");
                }
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                Response response = chain.proceed(request);

                ResponseBody responseBody = response.body();

                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                Buffer buffer = source.buffer();
                Logger.v("Rretrofit", buffer.clone().readString(Charset.defaultCharset()));
                if (response.headers().size() > 0) {
                    Logger.v("Retrofit", "---------- Response Headers ----------");
                    for (String headerName : response.headers().names()) {
                        Logger.v("Retrofit", headerName + ": " + response.headers().get(headerName));
                    }
                    Logger.v("Retrofit", "---------- Response Headers ----------");
                }

                return response;
            }
        };
    }
}
