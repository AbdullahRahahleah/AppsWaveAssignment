
This is the Assignment for Abdullah Rahahleh.
I have implemented the following:
1- Login using JWT, where you can do the following:

A. Signup using the needed information:
    
    {
    
    "fullName":"abdullah",
    
    "dateOfBirth" : "1995-02-26",
    
    "email" : "man.real300@yahoo.com",
    
    "password":"12345678",
    
    "role":"NORMAL"
    }

Here You need to define the role for the user, which is one of those values
NORMAL,CONTENT_WRITER,ADMIN

Date of birth also has a specific format "yyyy-mm-dd"

In addition, other fields are not blank.

I have created a default admin user.

B. singin operation, sample request

    {
    "email" : "admin@gmail.com",
    "password":"admin"
    }
This is the deafult user, where i do generate token and refresh token.

C. I do define in the attached file, how to signup the three types of roles
and how to signin.

D. I do implement the refresh token, where you send the refresh token which generated
at signin step, then the API will return a new valid token.


2- I do define a write operation, matching the business defined in the assignment
with taking into consideration the role validation.

You need to change the authType to Bearer token then pass the token which you got at signin step.


3- I do implement the read operation, matching the bussiness defined in the assignment
I do read the logged in user to determine if his role allowed him to do the read according to the news status.

4- I do implement the approve operation, where the user should pass the news title, to change it is status from pending to approved.

5- I do implement the delete operation, but here I did define another way to distinguish between user roles, here i defined two urls (delete and forceDelete), where forceDelete authorized only for the admin role.

Notes:

1- I define a customized validators

A. localdate validator for birthdate and news date.

B. Role validator, which validate the signup role value matching the options.

2- I define a schedule which will run every mid-night to update the column isDeleted
in the news entity, (tested by changing the crons to run every minute, and checking the behavior).


3- I define GlobalExceptionHandler, which will return a more descriptive response for the user to define the problem.


4- Everything placed in proper package and wel-formated code.

5- I did a local test using postman.