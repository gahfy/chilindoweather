package net.gahfy.chilindoweather.injection.module;

import android.support.annotation.NonNull;
import android.util.Log;

import net.gahfy.chilindoweather.BuildConfig;
import net.gahfy.chilindoweather.model.api.ApiWeather;
import net.gahfy.chilindoweather.network.OpenWeatherMapApi;
import net.gahfy.chilindoweather.utils.rxandroid.ApiUtils;
import net.gahfy.chilindoweather.utils.rxandroid.Schedulers;

import java.io.IOException;
import java.util.concurrent.Callable;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import io.reactivex.Observable;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static net.gahfy.chilindoweather.utils.constants.ApiConstants.BASE_URL;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.QUERY_API_KEY;
import static net.gahfy.chilindoweather.utils.rxandroid.ApiUtils.weatherMockPath;

/**
 * Module which provides all required dependencies about network
 */
@Module
public class NetworkModule {
    @NonNull
    private static final NetworkModule instance = new NetworkModule();

    private NetworkModule() {
    }

    @NonNull
    public static NetworkModule getInstance() {
        return instance;
    }

    @Provides
    @Reusable
    @NonNull
    static OpenWeatherMapApi provideOpenWeatherMapApi(@NonNull Retrofit retrofit) {
        return new OpenWeatherMapApi() {
            @Override
            public Observable<ApiWeather> getWeather(double gpsLatitude, double gpsLongitude) {
                return Observable.fromCallable(new Callable<ApiWeather>() {
                    @Override
                    public ApiWeather call() throws Exception {
                        return ApiUtils.getUrl(weatherMockPath, ApiWeather.class);
                    }
                });
            }
        };
    }

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

    @Provides
    @Reusable
    @NonNull
    static OkHttpClient provideClient(@Named("get_param_injector") Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

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

                Log.e("gahfy", url.toString());

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
    }
}
