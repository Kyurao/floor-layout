# floor-layout
A software application that helps with creating floor layout. The application allows storing legal rooms and recallspreviously added rooms.
Also, the application allows drawing accepted rooms using html5 canvas.

For simulation persistence of all data into DB I used H2 in-memory DB. In this case, we don't need a separate DB but all changes after closing the app will not be saved.

The application is completed for work in any web browser, but if you want to check it by Postman or any other familiar program you can use examples below:
(the coordinates must be in the range of 0 to 200)

Validate room   | method POST   | url: localhost:8080/validateRoom  | body {"room": [{"x": 0, "y": 0}, {"x": 0, "y": 150}, {"x": 150, "y": 150}, {"x": 150, "y": 0}]}

Create new room | method POST   | url: localhost:8080/room/add      | body {"room": [{"x": 0, "y": 0}, {"x": 0, "y": 150}, {"x": 150, "y": 150}, {"x": 150, "y": 0}]}

Get room        | method GET    | url: localhost:8080/room/1

Get all rooms   | method GET    | url: localhost:8080/room/all

Edit room       | method PUT    | url: localhost:8080/room/update/1 | body {"room": [{"x": 0, "y": 0}, {"x": 0, "y": 200}, {"x": 200, "y": 200}, {"x": 200, "y": 0}]}

Delete room     | method DELETE | url: localhost:8080/room/delete/1
