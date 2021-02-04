# Healthcareenrollmentsystem
Healthcareenrollmentsystem

1)  First start the servic here we are getting all details using  name and we are maintain

2)  Open the postman open new tab and  select  post  method and  Under url section  copy  paster URL http://localhost:8080/Person/create
3) under  body section  paste  it  than  hit  enter it will create  new  enrolly in database
{
    
    "name": "Eleka",
    "activation": true,
    "dob": "03111995",
    "phone":2817866885,
    "relationship": "sister",
     "dependents": [{
     	"depName":"priyam",
     	"depDob":"12041996",
     	"status": true,
     	"relationship":"sister"
     }
    ]
}
 
 4) To  get   Enroll  details use   http://localhost:8080/Person/view/name?name=<name> to  get all user with all depandent
 5) To  delete am enroll  make Delete method call using  below  http://localhost:8080/Person/delete/name?name= < name>
 6) To udate enroll use  put method call with url http://localhost:8080/Person/modify/Eleka?name=priyam  and  body 
   {
    "dob": "06131998",
    "phone":2817866887
  }

