# University Service Center

![Head_img](./data/studentservices2019-20.png)

(Source: https://images.app.goo.gl/tB36itRUvU18vmu3A)

## An application that helps students to select courses and TAs to grade students.

University Service Center always strives to provide a welcoming, professional, and safe environment for 
all the students, parents, alumni, and faculty members. When I first became a freshman in *UBC*, I felt overwhelmingly 
stressful to look into and absorb all the information on the website in case I miss something important. However, *UBC* 
manages to render the whole process simple and straightforward by utilizing the descriptive and comprehensible entries
as well as informative structures on the website. From my perspective as a current enrolled student, I just enjoy the 
experience using the websites. Thus, software engineers did an outstanding job on this, and it obviously deserves me
to learn from them.  

- *Why this project is of interest to you?*

As for my personal project, it strikes me that it would be fun to figure out the mechanism behind the university 
service center, and the best way to understand something is to implement it. A wide range of people such as students and 
TAs can benefit from using my application. They would save a large amount of energy and time if this application is 
handy. Therefore, it is intriguing to build this project and share it on Github.
- *What will the application do?*

This application **aims** to mimic only a small portion of the behaviors which *UBC Student Service Center* exhibits 
such as registering courses. In addition, the 
project is expected to modify and improve some functions provided by *UBC Student Service Center*. As a *UBC*
student, you may experience the situation that you want to register a course, but you have not met the prerequisites. 
That can be frustrating sometimes. In this application, a 
student who pursues a specific specialization can design his/her course pathways based on current registered courses 
instead of checking back and forth for prerequisites of each course.
Also, you are able to save these pathways (course selections) according to your career plans. Some gadgets could 
be further implemented to improve the user experience. If you are identified as a teaching assistant rather than a 
student, 
you can grade students by a simple grade calculator. It is available to create a grading scheme for each of the
student in the same class. These functions will make grading effective and efficient since TAs only need to type in the
grades for each assignment and exam. 
- *Who will use it?*

This application targets current *UBC* students and TAs. It can be customized to refine students' experience when 
selecting optimal courses and TAs' counterpart when grading assignments.

Hopefully, this application will hone my software constructing skills (Java programming).

### Phase 1 - User Stories

In the context of this application:
> - As a user (student), I want to be able to add multiple courses to a schedule. 
> - As a user (student), I want to be able to view all the prerequisites of the current course selection.
> - As a user (student), I want to be able to delete a course from a schedule. 
> - As a user (student), I want to be able to view the detail of the course and schedule.
> - As a user (student), I want to be able to view grades of the assignments.
> - As a user (TA), I want to be able to see an array of students' ID in the given class to protect privacy.
> - As a user (TA), I want to add multiple assignments to student and grade them.
> - As a user (TA), I want to remove multiple assignments from a student. 
> - As a user (TA), I want to see the course that students have registered.
> - As a user (TA/Student), I want to sign up and login with my name and number. 

### Phase 2 - User Stories

> - As a user (Student), I want to save the courses that I have selected in the schedule to a file.
> - As a user (TA), I want to save all the grading status of an assignment to a file.
> - As a user (Student/TA), I want to be able to clear the existing users' data.
> - As a user (Student/TA), I want to be able to auto-save the existing state when I end the program.
> - As a user (Student/TA), I want to load all the users' data to the program automatically when the application starts.