package com.lididimi.restaurant.constants;

public class RestaurantConstants {
    public static final String MESSAGE_TEMPLATE = "{\"message\": \"%s\"}";

    public static final String SOMETHING_WENT_WRONG = String.format("Something went wrong.");
    public static final String INVALID_DATA = String.format("Invalid data.");
    public static final String NOT_FOUND = String.format("Not found.");

    // USER
    public static final String UNAUTHORIZED_ACCESS = String.format("Unauthorized access.");
    public static final String USER_NOT_FOUND = String.format("User not found.");
    public static final String USER_UPDATE_SUCCESS = String.format("User status updated successfully.");
    public static final String BAD_CREDENTIALS = String.format("Bad credentials.");
    public static final String UNAPPROVED = String.format("Wait for admin approval.");
    public static final String EMAIL_EXISTS = String.format("Email already exists.");
    public static final String PASSWORD_UPDATE_SUCCESS = String.format("Password changed successfully.");
    public static final String INCORRECT_OLD_PASSWORD = String.format("Incorrect old password.");
    public static final String CHECK_EMAIL = String.format("Check your email for link to reset password.");
    public static final String EMAIL_NOT_FOUND = String.format("Email not found.");
    public static final String TOKEN_EXPIRED = String.format("Token expired.");
    public static final String TOKEN_RESET_URL_BASE = "http://localhost:4200/reset-password?token=";

    // PRODUCT
    public static final String PRODUCT_ADD_SUCCESS = String.format("Successfully added product.");
    public static final String PRODUCT_UPDATE_SUCCESS = String.format("Product updated successfully.");
    public static final String PRODUCT_NOT_FOUND = String.format("Product not found.");
    public static final String PRODUCT_DELETE_SUCCESS = String.format( "Product deleted successfully.");

    //CATEGORY
    public static final String CATEGORY_ADD_SUCCESS = String.format("Category added successfully.");
    public static final String CATEGORY_NOT_FOUND = String.format("Category not found.");
    public static final String CATEGORY_UPDATE_SUCCESS = String.format("Category updated successfully.");
    public static final String ALREADY_EXISTS = String.format("Category name already exists");


    //BILL
    public static final String BILL_NOT_FOUND = String.format(MESSAGE_TEMPLATE, "Bill not found.");
    public static final String BILL_DELETE_SUCCESS = String.format(MESSAGE_TEMPLATE, "Bill deleted successfully.");
    public static final String PDF_GENERATION_FAILURE = String.format(MESSAGE_TEMPLATE, "Failed to generate PDF");

    public static final String STORE_LOCATION = "C:\\Users\\dimit\\Documents\\SOFTUNI\\restaurant_pdf";
}

