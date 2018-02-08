package empolyesecurity.testmvp.data.network;

import android.content.Context;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import empolyesecurity.testmvp.BuildConfig;
import empolyesecurity.testmvp.data.network.model.BlogResponse;
import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


public interface APIService {

    String ENDPOINT = "http://api.openweathermap.org/";
    String API_KEY = "aa9af8d39d6519b1d47dec305bd253a4";

   /* @GET("data/2.5/weather?APPID=" + API_KEY)
    Observable<BlogResponse> getWeatherForLatLon(@Query("lat") double lat, @Query("lng") double lng, @Query("units") String units);*/

  /*  @GET("data/2.5/weather?APPID=" + API_KEY)
    Observable<BlogResponse> getWeatherForCity(@Query("q") String city, @Query("units") String units);*/

    @GET("v2/5926ce9d11000096006ccb30")
    Observable<BlogResponse> getForecastForCity();

    class Factory {

        public static APIService create(Context context) {

            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(30, TimeUnit.SECONDS);
            builder.connectTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(60, TimeUnit.SECONDS);

            //builder.certificatePinner(new CertificatePinner.Builder().add("*.androidadvance.com", "sha256/RqzElicVPA6LkKm9HblOvNOUqWmD+4zNXcRb+WjcaAE=")
            //    .add("*.xxxxxx.com", "sha256/8Rw90Ej3Ttt8RRkrg+WYDS9n7IS03bk5bjP/UXPtaY8=")
            //    .add("*.xxxxxxx.com", "sha256/Ko8tivDrEjiY90yGasP6ZpBU4jwXvHqVvQI0GS3GNdA=")
            //    .add("*.xxxxxxx.com", "sha256/VjLZe/p3W/PJnd6lL8JVNBCGQBZynFLdZSTIqcO0SJ8=")
            //    .build());

            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
                builder.addInterceptor(interceptor);
            }

            //Extra Headers
//            builder.addNetworkInterceptor(chain -> {
//                Request request = chain.request().newBuilder().addHeader("Authorization", authToken).build();
//                return chain.proceed(request);
//            });

            int cacheSize = 10 * 1024 * 1024; // 10 MiB
            Cache cache = new Cache(context.getCacheDir(), cacheSize);
            builder.cache(cache);

           // builder.addInterceptor(new UnauthorisedInterceptor(context));
            OkHttpClient client = builder.build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.mocky.io/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            return retrofit.create(APIService.class);
        }
    }
}