package uk.usedcars.marketplace.dealers.auto.finance.utils

import android.content.Context
import android.content.SharedPreferences

object FavoriteManager {
    private const val PREF_NAME = "favorite_cars_pref"
    private const val KEY_FAVORITES = "favorites_set"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun getFavorites(context: Context): Set<String> {
        return getPrefs(context).getStringSet(KEY_FAVORITES, emptySet()) ?: emptySet()
    }

    fun toggleFavorite(context: Context, carId: String): Boolean {
        val prefs = getPrefs(context)
        val currentFavorites = prefs.getStringSet(KEY_FAVORITES, emptySet())?.toMutableSet() ?: mutableSetOf()
        
        val isNowFavorite = if (currentFavorites.contains(carId)) {
            currentFavorites.remove(carId)
            false
        } else {
            currentFavorites.add(carId)
            true
        }
        
        prefs.edit().putStringSet(KEY_FAVORITES, currentFavorites).apply()
        return isNowFavorite
    }
}
