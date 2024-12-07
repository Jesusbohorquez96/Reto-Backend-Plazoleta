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

    public static final String ID = "id";
    public static final String ROL = "rol";
    public static final String TO_ROL = "toRol";
    public static final String ROLES = "roles";
    public static final String TAG_ROLES = "Roles";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String PHONE_NUMBER = "^\\+[0-9]{1,13}$";
    public static final String NUMBERS_NAME = "^(?!\\d+$)[\\w\\s]+$";
    public static final String NAME = "name";
    public static final String PRIVATE = "294A404E635266556A586E327235753878214125442A472D4B6150645367566B";

    public static final String MESSAGE = "message";
    public static final String SPRING = "spring";
    public static final String NAME_REQUIRED = "Name is required";
    public static final String NIT_REQUIRED = "The NIT is required";
    public static final String ADDRESS_REQUIRED = "The address is required";
    public static final String PASSWORD_INVALID = "Password must be at least 8 characters long, and must include at least one number, one uppercase letter, one lowercase letter, and one special character";
    public static final String EMAIL_REQUIRED = "Email is required";
    public static final String EMAIL_INVALID_FORMAT = "Email must have a valid format";
    public static final String ID_DOCUMENT_REQUIRED = "Identity document is required";
    public static final String ID_DOCUMENT_NUMERIC = "Identity document must be numeric and cannot contain decimals";
    public static final String PHONE_REQUIRED = "Phone number is required";
    public static final String PHONE_INVALID = "Phone number must be a maximum of 13 characters and may include the '+' symbol";
    public static final String URL_REQUIRED = "The URL is required";
    public static final String URL_PAST = "The logo URL must be a valid URL";
    public static final String NIT_NUMERIC = "The NIT must be numeric";
    public static final String NAME_NUMBERS = "The name cannot be just numbers";
    public static final String DESCRIPTION_LONG = "Description is too long";
    public static final String DESCRIPTION_REQUIRED = "Description cannot be blank";
    public static final String DESCRIPTION_MAX_LENGTH_EXCEEDED = "The description must not exceed 120 characters";
    public static final String USER_MUST_BE_ADULT = "User must be at least 18 years old";
    public static final String USER_NOT_FOUND = "User must be an adult";
    public static final String USER_NOT_FOUND_MESSAGE = "User not found";

    public static final String USER_ID_TARGET = "userId";
    public static final String UNIQUE_NAME = "The restaurant name must be unique.";
    public static final String NIT_NAME = "The restaurant NIT must be unique.";
    public static final String USER_IDENTITY_DOCUMENT_TARGET = "userIdentityDocument";
    public static final String USER_PHONE_TARGET = "userPhone";
    public static final String USER_BIRTHDATE_TARGET = "userBirthdate";
    public static final String USER_EMAIL_TARGET = "userEmail";
    public static final String USER_PASSWORD_TARGET = "userPassword";
    public static final String USER_ROL_TARGET = "userRol";
    public static final String LAST_NAME = "lastName";
    public static final String IDENTITY_DOCUMENT= "identityDocument";
    public static final String PHONE = "phone";
    public static final String BIRTHDATE = "birthdate";
    public static final String EMAIL = "email";
    public static final String PASSWORD_SOURCE= "password";
    public static final String DESCRIPTION = "description";
    public static final String ROL_ID_TARGET = "rolId";
    public static final String ROL_ID_LIST = "rol_id";
    public static final String USERS = "users";
    public static final String TAG_USERS = "Users";
    public static final String ROLE =  "ROLE_";
    public static final String ROL_NAME_TARGET = "rolName";
    public static final String ROL_DESCRIPTION_TARGET = "rolDescription";

    public static final String TITLE = "Hexagonal Monolithic Category API";
    public static final String TERMS_OF_SERVICE_URL = "http://swagger.io/terms/";
    public static final String LICENSE_NAME = "Apache 2.0";
    public static final String LICENSE_URL = "http://springdoc.org";

    public static final String APP_DESCRIPTION = "${appdescription}";
    public static final String APP_VERSION = "${appversion}";
    public static final String MALFORMED_JWT = "Malformed JWT Exception";
    public static final String JWT_TOKEN = "JWT Token";
    public static final String INVALID_JWT = "Invalid or malformed JWT token";
    public static final String API_USERS = "API for user management";
    public static final String API_ROLES = "API for rol management";

    public static final String V3_API = "/v3/api-docs/**";
    public static final String SWAGGER_UI = "/swagger-ui.html";
    public static final String SWAGGER_UI_RESOURCES = "/swagger-ui/**";
    public static final String AUTH = "/auth/**";
    public static final String ALL_API = "/api/**";

    public static final String STATUS = "status";
    public static final String ERRORS = "errors";
    public static final String FIELD = "field";
    public static final String REJECTED_VALUE = "rejectedValue";
    public static final String NULL = "null";
    public static final String VALIDATION_FAILED = "Validation failed for one or more fields";

    public static final String INTERNAL_SERVER = "Internal server error occurred";
    public static final String OCCURRED_UNEXPECTED = "An unexpected error occurred";

    public static final String ROOT = "/";
    public static final String CREATE = "/create";
    public static final String LOGIN = "/login";
    public static final String AUTH_API = "/auth";

    public static final String RESTAURANTS = "/restaurants";
    public static final String RESTAURANTS_API = "Restaurant API";
    public static final String REGISTER_CUSTOMER = "/register_customer";
    public static final String REGISTER_AUX_BODEGA = "/register_aux_bodega";
    public static final String USER_ID = "/{userId}";
    public static final String ROL_ID = "/{rolId}";

    public static final String ROL_ADMIN = "hasAnyRole('admin')";
    public static final String ROL_OWNER = "hasAnyRole('propietario')";

    public static final Long ADMIN = 1L;
    public static final Long AUX_BODEGA = 2L;
    public static final Long CUSTOMER = 3L;

    public static final String JSON = "application/json";
    public static final String ERROR_JWT = "{ \"error\": \"Access denied: Invalid or malformed JWT token\" }";
    public static final String USER_CREATED = "User created successfully";

    public static final String ALL = "*";
    public static final String[] ALLOWED_METHODS = {"GET", "POST", "PUT", "DELETE", "OPTIONS"};
    public static final String HTTP = "*";

    private ValidationConstants() {
        throw new IllegalStateException("Utility class");
    }
}
