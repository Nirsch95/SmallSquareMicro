package com.pragma.powerup.smallsquearemicroservice.configuration;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final Long ADMIN_ROLE_ID = 1L;
    public static final Long OWNER_ROLE_ID = 2L;
    public static final Long EMPLOYEE_ROLE_ID = 3L;
    public static final Long CLIENT_ROLE_ID = 4L;
    public static final int MAX_PAGE_SIZE = 2;
    public static final String RESPONSE_MESSAGE_KEY = "message";
    public static final String RESTAURANT_CREATED_MESSAGE = "Restaurant created successfully";
    public static final String RESTAURANT_DELETED_MESSAGE = "Restaurant deleted successfully";
    public static final String RESPONSE_ERROR_MESSAGE_KEY = "error";
    public static final String WRONG_CREDENTIALS_MESSAGE = "Wrong credentials";
    public static final String NO_DATA_FOUND_MESSAGE = "No data found for the requested petition";
    public static final String RESTAURANT_ALREADY_EXISTS_MESSAGE = "A Restaurant already exists with the role provided";
    public static final String RESTAURANT_NOT_FOUND_MESSAGE = "No Restaurant found with the role provided";
    public static final String SWAGGER_TITLE_MESSAGE = "SmallSquare API Pragma Power Up";
    public static final String SWAGGER_DESCRIPTION_MESSAGE = "SmallSquare microservice";
    public static final String SWAGGER_VERSION_MESSAGE = "1.0.0";
    public static final String SWAGGER_LICENSE_NAME_MESSAGE = "Apache 2.0";
    public static final String SWAGGER_LICENSE_URL_MESSAGE = "http://springdoc.org";
    public static final String SWAGGER_TERMS_OF_SERVICE_MESSAGE = "http://swagger.io/terms/";
    public static final String USER_NOT_A_OWNER_MESSAGE = "The user is not a Owner";
    public static final String USER_NOT_FOUND_MESSAGE = "The user is not found";
    public static final String USER_NOT_FOUND_MICROSERVER_DOWN_MESSAGE = "The user is not found, microservice down";
    public static final String DISH_ALREADY_EXISTS_MESSAGE = "A Dish already exists with that name";
    public static final String DISH_CREATED_MESSAGE = "Dish created successfully";
    public static final String DISH_UPDATED_MESSAGE = "Dish updated successfully";
    public static final String DISH_NOT_FOUND_MESSAGE = "Dish is not found";
    public static final String CATEGORY_CREATED_MESSAGE = "Category created successfully";
    public static final String CATEGORY_ALREADY_EXISTS_MESSAGE = "A Category already exists with that name";
    public static final String EMPTY_FIELD_MESSAGE = "The field cannot be empty";
    public static final String INVALID_FORMAT_PHONE_MESSAGE = "Invalid format phone";
    public static final String INVALID_VALUE_DNI_NUMBER = "The ID number cannot have more than 20 digits.";
    public static final String INVALID_FORMAT_NAME_MESSAGE = "Invalid format name";
    public static final String INVALID_USER_TO_CREATE_UPDATE_DISH = "This user is not the owner of this restaurant";
    public static final String INVALID_TOKEN_MESSAGE = "Invalid token";
    public static final String UNALLOWED_USER_TO_USE_ENDPOINT = "This user is not allowed to use this endpoint";
}
