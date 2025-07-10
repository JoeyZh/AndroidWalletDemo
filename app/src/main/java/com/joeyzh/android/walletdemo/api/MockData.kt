package com.joeyzh.android.walletdemo.api

import android.content.Context
import com.google.gson.Gson
import com.joeyzh.android.walletdemo.model.CurrenciesObject
import com.joeyzh.android.walletdemo.model.CurrencyData
import com.joeyzh.android.walletdemo.model.LiveratesObject
import com.joeyzh.android.walletdemo.model.Tier
import com.joeyzh.android.walletdemo.model.Wallet
import com.joeyzh.android.walletdemo.model.WalletBalanceObject
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * @Date 2025/7/9
 * @Create Joey 周晔
 * @Description Mock Api data
 */
open class MockData {

    fun loadJsonFromAssets(context: Context, fileName: String): String? {
        return try {
            val inputStream = context.assets.open(fileName)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
            reader.close()
            stringBuilder.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun mockCurrencies(context: Context): List<CurrencyData> {
        val jsonString = loadJsonFromAssets(context, "currencies.json") ?: return listOf()
        val currencies = try {
            Gson().fromJson(jsonString, CurrenciesObject::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
        if (currencies != null) {
            return currencies.currencies
        }
        return listOf()
    }

    fun mockLiveRates(context: Context): List<Tier> {
        val jsonString = loadJsonFromAssets(context, "live-rates.json") ?: return listOf()
        val objects = try {
            Gson().fromJson(jsonString, LiveratesObject::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
        if (objects != null) {
            return objects.tiers
        }
        return listOf()
    }

    fun mockWalletBalances(context: Context): List<Wallet> {
        val jsonString = loadJsonFromAssets(context, "wallet-balance.json") ?: return listOf()
        val objects = try {
            Gson().fromJson(jsonString, WalletBalanceObject::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
        if (objects != null) {
            return objects.wallet
        }
        return listOf()
    }
}