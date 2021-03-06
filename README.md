Rattler Coders
===

# FAMU CISMO Library

# Updates on Progress of App:
(4/30/2020) You can view the [narration demo](https://github.com/FAMU-CIS-Library/CISMOLibrary/blob/master/Narration-Library.mp4) of this app by clicking the link.

(4/29/2020) Scope of Project and MVP required stories have been adjust due to previous issues during development of project.

(4/22/2020) Because of issues with our team using GitHub Organizations, we lost a lot of files that should have merged on our branches.
This along with other unforeseen issues with our team led to large delays in the progress of this progress.

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
The library of books in the academic adviser's office is currently kept track of via pencil and paper. As a department full of tech students we should bring the library into the 21st century and allow our library to be tracked electronically. The app will show the catalog of books and show their availability as well as letting students use their student accounts to checkout the books to and from the library.

### App Evaluation
- **Category:** Education
- **Mobile:** Uses push notifications to remind students when their book rentals are due and requires real time data on which books are available or checked out.
- **Story:** As students come to the academic advisor to rent books, instead of having to check in a old folder full of papers with students that graduated years ago, they can search digitally for valid students and the book(s) that want to be requested as well. 
- **Market:** This app's user base would be students in the CIS department. 
- **Habit:** This app would have students return as often as they need to rent/return books for the semester. Ideally it'd be once or twice a week.
- **Scope:** The scope of this project is to have a small set of users and a subset of the library books to "checkout" and "return" for this sprint. Having the capability to search, find, and checkout/return books to the library would be great to have, even with a small subset of the library. As the books increase, it's still just as simple to search and request books for rental.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**
[x] Register with FAMU Credentials
[x] Login using FAMU Credentials
[x] View CISMO Library Textbooks

**Optional Nice-to-have Stories**
[] Viewing more detailed book information
    [] Number of Book Copies Available
    [] Add books to a Wishlist/Waitlist
[] View Book Checked out in Profile
[] See notifications on book's due dates
[] Check out books

### 2. Screen Archetypes
* Register
    * Register with FAMU Credentials
        * Use valid FAMU Student ID
        * Use valid FAMU Email
* Login
    * Login using FAMU Credentials
        * Student ID
        * Password (Soon to be synced with FAMU Database)
* Catalog
    * Display CIS Library Books
        * Title
        * Author
        * Edition
* Profile
    * Dis
* Search
    * 

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Profile
* Catalog/Search

**Flow Navigation** (Screen to Screen)

* Register Activity
   * FAMU ID (required and must start with 300)
   * Student Name
   * Student Email (must end with @famu.edu)
   * Password
   * Button to Navigate to Login Activity if they've already registered
* Login Activity
   * FAMU ID (required and must start with 300)
   * Password
   * Button to Navigate to Login Activity if they've already registered

## Wireframes
<img src="wireframes-draft1.jpg" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 
### Models
#### Book
   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the book (default field - Format - "CIS-000") |
   | createdAt    | DateTime | date when book is created (default field) |
   | updatedAt   | DateTime | date when book is last updated (i.e. returned/checked out) (default field) |
   | bookTitle   | String   | Book Title |
   | author       | String   | author of library book |
   | description   | String   | description of textbook |
   | edition       | String   | edition or year of book publication |
   | pageNum       | Number   | number of pages in the textbook |
   | condition     | String   | condition of book upon return |
   | totalQuantity | Number | Number of books in library |
   | freeQuantity  | Number | Number of books for rent (pulled from netw. req.) |
   | coursesUsed   | List of Strings | Course that book is used for |

#### Student (Currently "User")
   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | studentId     | String   | unique id for the user post (default field) |
   | createdAt     | DateTime | date when Student is created (default field) |
   | updatedAt     | DateTime | date when Student is last updated (default field) |
   | studentNum    | Number   | FAMU Student Number (default field, must begin w/ '300') |
   | email         | String   | FAMU Student Email |
   | password      | String   | FAMU Password |
   | classification | String   | Student Classification |
   | bookRented   | Pointer to Book  | book being rented by student |
   | rentStatus   | Boolean   | Determines if they can check out a book (only 1 book at a time, no overdue books) |
#### Librarian/Admin (Stretch)
   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | adminId       | String   | unique id for the user post (default field) |
   | email         | Pointer to User | image author |
   | password      | String     | image that user posts |
### Networking
#### List of network requests by screen
- Register Screen   
  - (Create/POST) Create a new Student
  ```java
        ParseUser student = new ParseUser();
        student.setUsername(username);
        student.setPassword("my pass");
        student.setEmail("email@example.com");

    // other fields can be set just like with ParseObject
    student.put("phone", "650-253-0000");

    student.signUpInBackground(new SignUpCallback() {
    public void done(ParseException e) {
        if (e == null) {
          // Hooray! Let them use the app now.
        } else {
          // Sign up didn't succeed. Look at the ParseException
          // to figure out what went wrong
        }
      }
    });
    ```
- Login Screen   
  - (Read/GET) Login to app with studentNum or studentEmail 
  ```java
  // Create the ParseUser
    ParseUser user = new ParseUser();
    // Set core properties
    user.setUsername("joestevens");
    user.setPassword("secret123");
    user.setEmail("email@example.com");
    // Set custom properties
    user.put("phone", "650-253-0000");
    // Invoke signUpInBackground
    user.signUpInBackground(new SignUpCallback() {
      public void done(ParseException e) {
        if (e == null) {
          // Hooray! Let them use the app now.
        } else {
          // Sign up didn't succeed. Look at the ParseException
          // to figure out what went wrong
        }
      }
    });
  ```
- Home Feed/Library Feed Screen
  - (Read/GET) Query all books where freeQuantity > 0
    ```java
    ParseQuery<ParseObject> query = ParseQuery.getQuery("Book");
    query.whereGreaterThan("freeQuantity", 0);
    query.findInBackground(new FindCallback<ParseObject>() {
        public void done(List<ParseObject> bookList, ParseException e) {
            if (e == null) {
                if(bookList.size() == 1)
                    Log.d("Book", "Retrieved " + bookList.size() + " book.");
                else
                    Log.d("Book", "Retrieved " + bookList.size() + " books.");
            } else {
                Log.d("Book", "Error: " + e.getMessage());
            }
        }
    });
    ```
- Search Results Screen
  - (Read/GET) Query all books where freeQuantity > 0 and requested fields from search match data in book object
  <img src="searchscreen.gif" width=600>
  
- Book Detail Screen
  - (Read/GET) Query logged in user object
  - (Update/PUT) Update user profile image   
- Checkout Screen
  - (Read/GET) Query student's requested book to be checked out
  - (Update/PUT) Upon checkout, update student's rentStatus to false, update student's bookRented to bookId, decrease the freeQuantity in all books with the same book title 
