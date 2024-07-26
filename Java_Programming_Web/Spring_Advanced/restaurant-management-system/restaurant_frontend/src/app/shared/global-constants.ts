export class GlobalConstants {

    // Message
    public static genericError : string  = "Something went wrong. Please try again later.";
    public static unauthorized: string = "You are not authorized to access this page."
    public static productExistError: string = "Product already exists."
    public static productEdit: string = "Product updated successfully."
    public static productAdded: string = "Product added successfully."

    // Regex
    public static nameRegex : string = "^[a-zA-Z\\s]*$";
    public static emailRegex : string = "[A-Za-z0-9._%-]+@[A-Za-z0-9._%-]+\\.[a-z]{2,3}";
    public static contactNumberRegex : string = "^0[0-9]{9}$";

    // Variable
    public static error : string = "error";
}