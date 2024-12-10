package com.hexagonal.microservicio_plazoleta.constants;

public class ValidationConstants {

    public static final long TIME = 1000 * 60 * 60 * 24;

    public static final int ZERO = 0;
    public static final int DECIMALS = 2;
    public static final int SEVEN = 7;
    public static final int INTEGERS = 10;
    public static final int MAYOR = 18;
    public static final int MAX_NIT = 20;
    public static final int NAME_MAX_LENGTH = 50;
    public static final int EMAIL_MAX_LENGTH = 50;
    public static final int DESCRIPTION_MAX_LENGTH = 90;
    public static final int MAX_LENGTH = 120;

    public static final String ZERO_S = "0";
    public static final String INTEGERS_S = "10";

    public static final String ROL = "rol";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String PHONE_NUMBER = "^\\+[0-9]{1,13}$";
    public static final String NUMBERS_NAME = "^(?!\\d+$)[\\w\\s]+$";
    public static final String PRIVATE = "294A404E635266556A586E327235753878214125442A472D4B6150645367566B";
    public static final String OWNER_REQUIRED = "The Owner ID is required";

    public static final String MESSAGE = "message";
    public static final String RESTAURANT_REQUIRED = "Restaurant ID is required";
    public static final String NAME_REQUIRED = "Name is required";
    public static final String NIT_REQUIRED = "The NIT is required";
    public static final String ADDRESS_REQUIRED = "The address is required";
    public static final String PHONE_REQUIRED = "Phone number is required";
    public static final String PHONE_INVALID = "Phone number must be a maximum of 13 characters and may include the '+' symbol";
    public static final String URL_REQUIRED = "The URL is required";
    public static final String URL_PAST = "The logo URL must be a valid URL";
    public static final String URL_IMAGE = "The image URL must be valid and start with http or https.";
    public static final String NIT_NUMERIC = "The NIT must be numeric";
    public static final String NAME_NUMBERS = "The name cannot be just numbers";
    public static final String DESCRIPTION_REQUIRED = "Description  is required";
    public static final String DESCRIPTION_MAX_LENGTH_EXCEEDED = "The description must not exceed " + MAX_LENGTH + " characters";
    public static final String PRICE_INVALID = "The price of the dish must be a positive integer and greater than 0.";
    public static final String HTTPS = "^(http|https)://.*";
    public static final String PRICE_MANDATORY = "Price is mandatory";
    public static final String DISH_REQUIRED = "Dish ID is required";
    public static final String QUANTITY_REQUIRED = "Quantity is mandatory";
    public static final String SELECTED_DISHES = "selectedDishes";
    public static final String DISHES_SELECTED = "selected_dishes";
    public static final String RESTAURANT_ID_MAPPER = "restaurantId";
    public static final String RESTAURANT_ID_SOURCE = "restaurant.id";
    public static final String ID_RESTAURANT = "restaurant";
    public static final String ID_RESTAURANT_OWNER = "restaurant.ownerId";
    public static final String ID_OWNER = "ownerId";
    public static final String ORDER_ID = "order_id";
    public static final String ORDER = "order";
    public static final String ORDERS = "orders";

    public static final String CATEGORY_REQUIRED = "The category of the dish is mandatory.";
    public static final String UNIQUE_NAME = "The restaurant name must be unique.";
    public static final String NIT_NAME = "The restaurant NIT must be unique.";
    public static final String ROLE =  "ROLE_";
    public static final String INVALID_JWT = "Invalid or malformed JWT token";

    public static final String OWNER_NOT_DISH = "You are not the owner of this dish.";
    public static final String DISH_NOT_FOUND = "Dishes not found";
    public static final String V3_API = "/v3/api-docs/**";
    public static final String SWAGGER_UI = "/swagger-ui.html";
    public static final String SWAGGER_UI_RESOURCES = "/swagger-ui/**";
    public static final String AUTH = "/auth/**";
    public static final String ALL_API = "/api/**";
    public static final String ALL_API_DISHES = "/api/selected-dishes";
    public static final String API_ORDERS = "/api/orders";
    public static final String GET_RESTAURANT_ID = "/restaurant/{restaurantId}";

    public static final String STATUS = "status";
    public static final String ERRORS = "errors";
    public static final String FIELD = "field";
    public static final String REJECTED_VALUE = "rejectedValue";
    public static final String NULL = "null";
    public static final String VALIDATION_FAILED = "Validation failed for one or more field ";
    public static final String ROOD_ID = "/{id}";

    public static final String ORDER_PROCESS = "The user already has an order in process.";
    public static final String SELECTED_DISHES_SAME_RESTAURANT = "All selected dishes must belong to the same restaurant. Invalid dish IDs: ";
    public static final String ERROR_HANDLER = "An unexpected error occurred while processing the request";
    public static final String ORDER_MUST_DISH = "An order must contain at least one dish.";
    public static final String RESTAURANT_NOT_EXIST = "The specified restaurant does not exist.";
    public static final String USER_SECURITY = "User ID is missing in the security context";
    public static final String DISH_NOT_FOUNT = "Dish not found";
    public static final String DISH_NOT_FOUNT_ID = "Dish not found: ID ";
    public static final String DISH_NOT_ACTIVE = "Dish is not active: ID ";
    public static final String DISH_PRICE_ZERO = "Dish price must be greater than zero.";
    public static final String QUANTITY_ZERO = "Quantity must be greater than zero.";
    public static final String ORDER_CANNOT_NULL = "Order cannot be null";

    public static final String DISH_WITH_NAME = "A dish with the name '";
    public static final String ALREADY_EXISTS_RESTAURANT = "' already exists in this restaurant.";

    public static final String OCCURRED_UNEXPECTED = "An unexpected error occurred";
    public static final String CREATE = "/create";
    public static final String USER_NOT_OWNER_RESTAURANT = "The user is not the owner of the restaurant.";
    public static final String RESTAURANT_ID = "restaurant_id";
    public static final String RESTAURANTS = "/restaurants";
    public static final String RESTAURANTS_API = "Restaurant API";
    public static final String DISHES = "/Dishes";
    public static final String DISHES_API = "Dishes API";
    public static final String DISHES_API_AUT= "/dishes/**";
    public static final String ROL_OWNER = "hasAnyRole('propietario')";
    public static final String ROL_ADMIN = "hasAnyRole('admin')";
    public static final String ROL_CUSTOMER = "hasAnyRole('cliente')";
    public static final String ROLE_AUT_ALL = "ROLE_propietario";


    public static final String JSON = "application/json";
    public static final String ERROR_JWT = "{ \"error\": \"Access denied: Invalid or malformed JWT token\" }";

    public static final String ALL = "*";
    public static final String[] ALLOWED_METHODS = {"GET", "POST", "PUT", "DELETE", "OPTIONS"};
    public static final String HTTP = "*";

    private ValidationConstants() {
        throw new IllegalStateException("Utility class");
    }
}
