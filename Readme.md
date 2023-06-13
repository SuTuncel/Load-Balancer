We have 3 different servers which use TCP for transfer protocol.
According to the documentation of the project we should select 3 of 4 request types to implement.
So we did not implement UDP protocol for video streaming.

Also in our project the flow as follows:
1. Make a request via Client.java.
2. Load Balancer decides which server the request should go.
3. Server receives the request and executes it.
4. It returns a response to the Client via Load Balancer.

In this project

To be able to run the project:
1. Run LoadBalancer.java
2. Run DirectoryListingServer.java
3. Run ComputationServer.java
4. Run FileTransferServer.java
5. Run Client.java

After running to Client.java, you can create requests to the servers.
When you create a request, the Load Balancer will automatically send the request to the correct server.

There is Assets folder in the src, when you choose "File Transfer" option it copies Test.txt file into the Assets folder
All servers behave first come first serve approach.

Challenges that we have faced during the project:
1 - Consistency of port numbers among the Java classes. We just spent a lot of time to figure the correct structure of port numbers out
2 - NullPointerException: In some parts of the project, NullPointerException error appeared many times. It was hard to handle those exceptions.
3 - Dynamic or Static status of Load Balancer: We had a hard time while understanding the static and dynamic load balancer concepts.
We could not implement dynamic load balancer for this project. We only implemented static one.


