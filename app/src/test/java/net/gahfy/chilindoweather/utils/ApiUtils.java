package net.gahfy.chilindoweather.utils;


import android.support.annotation.NonNull;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ApiUtils {
    public static String weatherMockPath = "";

    public static <T> T getUrl(@NonNull String jsonPath, Class<T> expectedType) throws Exception {
        final StringBuilder buf = new StringBuilder();

        final String javaBuildClassesFolder = ApiUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String assetsPath = javaBuildClassesFolder.replace("/build/intermediates/classes/test/debug", "/src/test/assets/api_mocks/" + jsonPath);
        assetsPath = assetsPath.replace("/build/intermediates/classes/test/release", "/src/test/assets/api_mocks/" + jsonPath);

        final FileInputStream inputStream = new FileInputStream(assetsPath);
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String str = bufferedReader.readLine();
        while (str != null) {
            buf.append(str);
            str = bufferedReader.readLine();
        }
        inputStream.close();
        bufferedReader.close();

        final Moshi moshi = new Moshi.Builder().build();
        final JsonAdapter<T> jsonAdapter = moshi.adapter(expectedType);

        return jsonAdapter.fromJson(buf.toString());
    }
}
