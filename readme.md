# TICKETS DATA MANAGER

This program retrieves data from `tickets.json`:

- The fastest flight for each carrier
- Difference between median and average value ticket price

At the moment the program works only with tickets where the origin is Vladivostok and the destination is Tel-Aviv. 

The example of data :
```json
{
  "tickets": [{
    "origin": "VVO",
    "origin_name": "Владивосток",
    "destination": "TLV",
    "destination_name": "Тель-Авив",
    "departure_date": "12.05.18",
    "departure_time": "16:20",
    "arrival_date": "12.05.18",
    "arrival_time": "22:10",
    "carrier": "TK",
    "stops": 3,
    "price": 12400
  }, {
    "origin": "VVO",
    "origin_name": "Владивосток",
    "destination": "TLV",
    "destination_name": "Тель-Авив",
    "departure_date": "12.05.18",
    "departure_time": "17:20",
    "arrival_date": "12.05.18",
    "arrival_time": "23:50",
    "carrier": "S7",
    "stops": 1,
    "price": 13100
  }, {
    "origin": "VVO",
    "origin_name": "Владивосток",
    "destination": "TLV",
    "destination_name": "Тель-Авив",
    "departure_date": "12.05.18",
    "departure_time": "6:10",
    "arrival_date": "12.05.18",
    "arrival_time": "16:15",
    "carrier": "S7",
    "stops": 0,
    "price": 17400
  }]
}
```