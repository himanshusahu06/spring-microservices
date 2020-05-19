# Netflix zuul API gateway server

### Api gateway
Common features that needs to implemented for all microservices.
* Authentication, authorization and security.
* Rate limiting
* Fault tolerance
* Service aggregation

Instead of calling microservices directly, all the calls will go through API gateway. Api gateway will take care of providing common features as listed above.