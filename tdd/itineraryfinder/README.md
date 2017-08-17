# Business Requirements
Your task is to build a tool for the ticketing desk of an airline to find the best possible itinerary for
a customer at the desk who wants to reach a particular destination at the earliest possible time.
You may assume that the customer doesn’t care about price or the number of connecting
flights; they just want to land at their destination as soon as possible.

Your tool should take as input 
1) a list of scheduled future flights, 
2) the origin airport name, and
3) the destination airport name. It should produce an textual itinerary which gets the customer to
the destination as soon as possible. For example, you could ingest the flight data as JSON:
```
[
    {
        "flightNumber": 117,
        "sourceAirport": "SFO",
        "destinationAirport": "OAK",
        "departureTime": "2017-01-25T22:17:05Z",
        "arrivalTime": "2017-01-25T22:21:00Z"
    },
    {
        "flightNumber": 451,
        "sourceAirport": "OAK",
        "destinationAirport": "IAD",
        "departureTime": "2017-01-26T04:01:00Z",
        "arrivalTime": "2017-01-26T10:21:00Z"
    },
    {
        "flightNumber": 453,
        "sourceAirport": "SFO",
        "destinationAirport": "IAD",
        "departureTime": "2017-01-26T04:00:00Z",
        "arrivalTime": "2017-01-26T10:24:00Z"
    }
]
```
Given these flights, a customer at the desk in SFO who wants to get to IAD should follow this
itinerary:

```
Board flight 117 to depart SFO at 2017-01-25T22:17:05Z and arrive at OAK at 2017-01-25T22:21:00Z.
Board flight 451 to depart OAK at 2017-01-26T04:01:00Z and arrive at IAD at 2017-01-26T10:21:00Z.
```

One final requirement- an itinerary is not valid if the customer doesn’t have at least 20 minutes
to get between their arrival gate and their departure gate during a layover. Since the layover at
OAK in the above example is longer than 20 minutes, the above itinerary is valid.

# Usage
- Requires Java 1.8+
- On Linux, OSX and Windows 10+ powershell use ```./gradlew``` and on Windows 7, 8 use ```gradlew```.
- ```./gradlew``` is used by default in the below commands
- To Run the application
```
./gradlew run
```
- To run tests
```
./gradlew build
# test reports are generated and located in build/reports/tests/test/index.html 
```
- Import project through Intellij: File -> Open -> build.gradle

# Note
- I organize code retreat event every year (it's an 8 hour even of pair programming, tdd, etc...)
- Here's the list of screen casts I have made and collected:
  - https://www.youtube.com/playlist?list=PL51Z0kRZnNoFoSiXtwEe8Af6_ImP8pllk
- I have also made the screen cast of this assignment:
  - https://www.youtube.com/watch?v=mv-aHMdUXVE&index=33&list=PL51Z0kRZnNoFoSiXtwEe8Af6_ImP8pllk&t=675s 
- The screen cast doesn't contain the complete working product as I couldn't put all of the requirements into 1-2 hours screen cast. However, this should reflect the big part of the business logic and a good representation on how I approach programming on a daily basis with TDD and incremental approach.  
 