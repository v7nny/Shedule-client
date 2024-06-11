# Android клиент для расписания колледжа/университета
Перед использованием следует настроить Retrofit
1. Создать файл `RetrofitGetter.kt` по пути `app/src/main/java/com/android/shedule/retrofit/`
2. В файле RetrofitGetter.kt:
\
`fun getRetrofit(): Retrofit = Retrofit.Builder()`
\
&nbsp; &nbsp; &nbsp; &nbsp; `.baseUrl("Ваш URL")`
\
&nbsp;  &nbsp; &nbsp; &nbsp; `.addConverterFactory(GsonConverterFactory.create()).build()`
