Summary:

The program simulates working of a post office, where 50 customers arrive to the post office and request the 3 postal workers, one at a time, to perform different task for them like buying stamps, mailing a letter, or mailing a package. Each task is different and hence the duration of time that the postal worker takes to finish each task is different. A postal worker can start serving the next customer only when he is finished with the current customer. Till then, the other customers need to wait for their turn. All the tasks need to be performed in a proper order. For example – the postal worker should first be assigned to a customer, only then the customer can request that particular postal worker to perform his/her task. Therefore, there needs to be mutual exclusion between the different events and this is being controlled by the use of semaphores. Minimum semaphores need to be assigned to perform various activities like allowing only a certain number of customers to enter the post office, or allowing a task to be run only after some other task is accomplished. There are some assumptions that have been made. For example- 60 seconds are equivalent to 1 second, so that the program runs faster.
In this way, all the 50 customers would eventually enter the post office, choose their task, get worker assigned to them, request that worker to perform their respective task, and finally go out to make room for other customers.

How to compile and run:
To compile the source code, use the following command:

javac Worker.java Customer.java PostOffice.java

To run the program, type the following command:

java PostOffice

Difficulties Encountered:

- Since it was required that each thread should perform its own operation, hence printing which postal worker is serving which customer and similarly which customer asks which postal worker to do a task was  challenging. This is because there is a separate thread for both customers and workers. So, there needs to be a mechanism for the worker thread to come to know about the customer number it is serving. Similarly, a mechanism is required for each customer thread to know which postal worker it has been assigned to. I use a queue to store the customer number each time the customer enters the shop and reaches the worker desk. Any free postal worker thread that is available would then dequeue the queue and gets assigned to that particular customer. Also, there is a field in every customer object that stores which worker has been assigned to it. This field becomes useful when the customer requests its corresponding worker to do a task in its own thread
- The placement of the semaphores was also very challenging. I had to also make sure that I was using minimum number of semaphores to achieve the target, which made it even more challenging.

What Did I Learn?

- Reading about semaphores is different, but when I actually used them in my program to bring mutual exclusion, then I really understood their proper working.
- I also learnt how to efficiently use semaphores because one can use so many semaphores to achieve the same result, but performing the same function by using minimum number of semaphores requires good programming knowledge. Hence, this project helped me learn how to not only write codes, but how to do efficient programming.
