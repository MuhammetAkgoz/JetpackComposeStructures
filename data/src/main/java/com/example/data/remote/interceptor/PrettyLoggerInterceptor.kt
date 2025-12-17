package com.example.data.remote.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class PrettyLoggerInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val startNs = System.nanoTime()

        // 📤 REQUEST (GİDEN İSTEK)
        Log.d("API_TAG", "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
        Log.d("API_TAG", "🚀 REQUEST: ${request.method} ${request.url}")

        val response: Response
        try {
            response = chain.proceed(request)
        } catch (e: Exception) {
            Log.e("API_TAG", "❌ REQUEST FAILED: ${e.message}")
            throw e
        }

        val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)

        // 📥 RESPONSE (GELEN CEVAP)
        val responseBodyString = response.body?.string() ?: ""

        val prettyJson = try {
            if (responseBodyString.trim().startsWith("{")) {
                JSONObject(responseBodyString).toString(4)
            } else if (responseBodyString.trim().startsWith("[")) {
                JSONArray(responseBodyString).toString(4)
            } else {
                responseBodyString
            }
        } catch (e: Exception) {
            responseBodyString
        }

        val icon = if (response.isSuccessful) "✅" else "❌"

        Log.d("API_TAG", "$icon RESPONSE: ${response.code} (${tookMs}ms)")
        if (prettyJson.isNotEmpty()) {
            Log.d("API_TAG", "📄 BODY:\n$prettyJson")
        }
        Log.d("API_TAG", "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")

        return response.newBuilder()
            .body(responseBodyString.toResponseBody(response.body?.contentType()))
            .build()
    }
}