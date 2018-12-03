## Parking lot app

Initial requirements see by link [description](Part%20A%20-%20Java%20and%20DB%20design%20and%20code%20assignment.docx).

### Rest API:
- POST /parking/occupy-slot - occupy parking slot
- POST /parking/free-slot?ticketId={int} - free parking slot
- GET /reports/num-of-parks?from={yyyy-MM-dd'T'HH:mm:ss}&to={yyyy-MM-dd'T'HH:mm:ss} - get total number of cars parked for passed period of time
- GET /reports/avg-num-of-parks?from={yyyy-MM-dd'T'HH:mm:ss}&to={yyyy-MM-dd'T'HH:mm:ss} - get average number of cars parked for passed period of time

### Class diagram:
![](class-diagram.png)

### DB design:
![](db-diagram.png)