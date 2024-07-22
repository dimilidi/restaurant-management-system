package com.lididimi.restaurant.constants;

public class RestaurantConstants {
    public static final String MESSAGE_TEMPLATE = "{\"message\": \"%s\"}";

    public static final String SOMETHING_WENT_WRONG = String.format(MESSAGE_TEMPLATE, "Something went wrong.");
    public static final String INVALID_DATA = String.format(MESSAGE_TEMPLATE, "Invalid data.");
    public static final String NOT_FOUND = String.format(MESSAGE_TEMPLATE, "Not found.");

    // USER
    public static final String UNAUTHORIZED_ACCESS = String.format(MESSAGE_TEMPLATE, "Unauthorized access.");
    public static final String USER_NOT_FOUND = String.format(MESSAGE_TEMPLATE, "User not found.");
    public static final String USER_UPDATE_SUCCESS = String.format(MESSAGE_TEMPLATE, "User status updated successfully.");
    public static final String BAD_CREDENTIALS = String.format(MESSAGE_TEMPLATE, "Bad credentials.");
    public static final String UNAPPROVED = String.format(MESSAGE_TEMPLATE, "Wait for admin approval.");
    public static final String EMAIL_EXISTS = String.format(MESSAGE_TEMPLATE, "Email already exists.");
    public static final String PASSWORD_UPDATE_SUCCESS = String.format(MESSAGE_TEMPLATE, "Password changed successfully.");
    public static final String INCORRECT_OLD_PASSWORD = String.format(MESSAGE_TEMPLATE, "Incorrect old password.");
    public static final String CHECK_EMAIL = String.format(MESSAGE_TEMPLATE, "Check your email for link to reset password.");
    public static final String EMAIL_NOT_FOUND = String.format(MESSAGE_TEMPLATE, "Email not found.");
    public static final String TOKEN_EXPIRED = String.format(MESSAGE_TEMPLATE, "Token expired.");
    public static final String TOKEN_RESET_URL_BASE = "http://localhost:4200/reset-password?token=";

    // PRODUCT
    public static final String PRODUCT_ADD_SUCCESS = String.format(MESSAGE_TEMPLATE, "Successfully added product.");
    public static final String PRODUCT_UPDATE_SUCCESS = String.format(MESSAGE_TEMPLATE, "Product updated successfully.");
    public static final String PRODUCT_NOT_FOUND = String.format(MESSAGE_TEMPLATE, "Product not found.");
    public static final String PRODUCT_DELETE_SUCCESS = String.format(MESSAGE_TEMPLATE, "Product deleted successfully.");

    //CATEGORY
    public static final String CATEGORY_ADD_SUCCESS = String.format(MESSAGE_TEMPLATE, "Category added successfully.");
    public static final String CATEGORY_NOT_FOUND = String.format(MESSAGE_TEMPLATE, "Category not found.");
    public static final String CATEGORY_UPDATE_SUCCESS = String.format(MESSAGE_TEMPLATE, "Category updated successfully.");

    //BILL
    public static final String BILL_NOT_FOUND = String.format(MESSAGE_TEMPLATE, "Bill not found.");
    public static final String BILL_DELETE_SUCCESS = String.format(MESSAGE_TEMPLATE, "Bill deleted successfully.");






    public static final String STORE_LOCATION = "C:\\Users\\dimit\\Documents\\SOFTUNI\\restaurant_pdf";
}

