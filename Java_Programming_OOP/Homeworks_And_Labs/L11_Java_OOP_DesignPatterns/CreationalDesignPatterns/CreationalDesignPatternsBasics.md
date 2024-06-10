
# Singleton Design Pattern
The Singleton pattern is a creational design pattern that ensures a class has only one instance
and provides a global point of access to that instance.  
We use it when we want one instance of a class to be reused throughout the entire application.
For example, when establishing a connection to a database, server; caching data, or logging errors in the system, etc. 
The idea is to open one connection and keep it open, reusing certain logic inside.

## Key points of the Singleton pattern:

1. **Single Instance:**  
It guarantees that a class has only one instance by controlling its instantiation process.

2. **Global Access:**  
It provides a global point of access to the instance, allowing code to access it from anywhere within the application.

3. **Lazy Initialization:**  
The instance is typically created only when it is first requested, also known as lazy initialization.
This helps conserve resources by not creating the instance until it's needed.

4. **Thread Safety:**  
Implementations need to consider thread safety, ensuring that multiple threads can't create multiple instances concurrently.


## Singleton Pattern implementation

+ It has a private constructor.
+ Private static field, which holds the instance of this class, meaning once created, it becomes existing.
+ Public static method – returns the instance, which once created, never changes.


## Pros of the Singleton pattern:

1. **Global Access:**   
Provides a single point of access to a shared resource, making it easy to manage and control.

2. **Resource Management:**  
It can help manage resources that are shared across the application, ensuring they are used efficiently.

3. **Lazy Initialization:**  
Allows resources to be allocated only when needed, improving performance and resource utilization.

## Cons of the Singleton pattern:

1. **Global State:**
The Singleton pattern introduces global state into an application, which can make code harder to understand and maintain. 
Violates the single responsibility principle.

2. **Testing Difficulty:**  
Due to its global state (everything is static and shared throughout the entire system), singletons can make unit testing more difficult as they can introduce dependencies and side effects.

3. **Concurrency Issues:**  
Implementations need to carefully consider concurrency issues to ensure thread safety, which can add complexity to the code.


