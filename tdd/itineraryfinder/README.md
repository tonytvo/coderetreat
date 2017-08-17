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
- Required Java 1.8+
- On Linux, OSX, Windows 10+ powershell:
```
./gradlew run
```
- On Windows 7, 8 (not including Windows 10+ powershell)
```
gradlew run
```
- Import project through Intellij: File -> Open -> build.gradle