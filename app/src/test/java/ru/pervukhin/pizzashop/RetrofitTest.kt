package ru.pervukhin.pizzashop

import android.util.Log
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.pervukhin.pizzashop.data.internet.DishData
import ru.pervukhin.pizzashop.data.internet.DishService
import ru.pervukhin.pizzashop.domain.Dish

class RetrofitTest {
    lateinit var mockWebServer: MockWebServer
    lateinit var dishesService: DishService
    val json = "[ {\n" +
            "        \"id\": 1,\n" +
            "        \"name\": \"Ветчина и грибы\",\n" +
            "        \"price\": 345,\n" +
            "        \"description\": \"Ветчина,шампиньоны, увеличинная порция моцареллы, томатный соус\",\n" +
            "        \"image_url\": \"https://dodopizza-a.akamaihd.net/static/Img/Products/e482d5c7b107456783a0e0100952d05f_292x292.webp\",\n" +
            "        \"category\": \"Пицца\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 2,\n" +
            "        \"name\": \"Баварские колбаски\",\n" +
            "        \"price\": 345,\n" +
            "        \"description\": \"Баварские колбаски, ветчина,пикантная пепперони, острая чоризо,томатный соус\",\n" +
            "        \"image_url\": \"https://dodopizza-a.akamaihd.net/static/Img/Products/18dbb12240b041a191c9dc73c9c1f4ef_292x292.webp\",\n" +
            "        \"category\": \"Пицца\"\n" +
            "    }]"

    @Before
    fun setUp(){
        mockWebServer = MockWebServer()
        dishesService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(DishService::class.java)
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }

    @Test
    fun testGetDishes() = runTest{
        val mockResponse = MockResponse()
        mockResponse.setBody(json)
        mockWebServer.enqueue(mockResponse)

        val response = dishesService.getAll().body()
        mockWebServer.takeRequest()

        val dishData1 = DishData(1,"Ветчина и грибы","Ветчина,шампиньоны, увеличинная порция моцареллы, томатный соус","https://dodopizza-a.akamaihd.net/static/Img/Products/e482d5c7b107456783a0e0100952d05f_292x292.webp", 345, Dish.CATEGORY_PIZZA)
        val dishData2 = DishData(2,"Баварские колбаски","Баварские колбаски, ветчина,пикантная пепперони, острая чоризо,томатный соус","https://dodopizza-a.akamaihd.net/static/Img/Products/18dbb12240b041a191c9dc73c9c1f4ef_292x292.webp", 345, Dish.CATEGORY_PIZZA)
        Assert.assertEquals(dishData1, response?.get(0))
        Assert.assertEquals(dishData2, response?.get(1))
    }

    @Test
    fun testEmptyBody() = runTest{
        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        val response = dishesService.getAll().body()
        mockWebServer.takeRequest()

        val expected: List<DishData> = listOf()
        Assert.assertEquals(expected, response)
    }
}