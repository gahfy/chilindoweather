package net.gahfy.chilindoweather.model.api;

import android.support.annotation.Nullable;

import com.squareup.moshi.Json;

import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_CONDITION_CONDITION_NAME;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_CONDITION_GROUP_NAME;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_CONDITION_ICON_ID;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_CONDITION_ID;

/**
 * Weather condition as it is returned by the OpenWeatherMap API
 * @version 2.5
 */
// Safe as we want API POJOs to be specific (in case API changes) instead of using inheritance
@SuppressWarnings("common-java:DuplicatedBlocks")
public final class ApiCondition {
    /**
     * Unique identifier of the weather condition
     */
    @Json(name = JSON_CONDITION_ID)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Integer id;

    /**
     * Name of the group of the weather parameters (Rain, Snow, Extreme, .)
     */
    @Json(name = JSON_CONDITION_GROUP_NAME)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private String groupName;

    /**
     * Name of the weather condition within the group
     */
    @Json(name = JSON_CONDITION_CONDITION_NAME)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private String conditionName;

    /**
     * Unique identifier of the weather icon
     */
    @Json(name = JSON_CONDITION_ICON_ID)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private String iconId;

    /**
     * Returns the unique identifier of the weather condition.
     *
     * @return the unique identifier of the weather condition
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Integer getId() {
        return id;
    }

    /**
     * Returns the name of the group of the weather parameters (Rain, Snow, Extreme, .).
     *
     * @return the name of the group of the weather parameters (Rain, Snow, Extreme, .)
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final String getGroupName() {
        return groupName;
    }

    /**
     * Returns the name of the weather condition within the group.
     *
     * @return the name of the weather condition within the group
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final String getConditionName() {
        return conditionName;
    }

    /**
     * Returns the unique identifier of the weather icon.
     *
     * @return the unique identifier of the weather icon
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final String getIconId() {
        return iconId;
    }
}
