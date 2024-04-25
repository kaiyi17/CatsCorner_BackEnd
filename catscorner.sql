create database catscorner;

use catscorner;
drop TABLE courses;

CREATE TABLE courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    schedule VARCHAR(255)
);

INSERT INTO courses (id, name, description, schedule) VALUES 
(1, "Beginner swing", "You will learn to familiarize yourself with the music and several basic steps (Lindy Hop, Charleston, Jig Walk, …). You will learn useful tips for navigating social dance parties (“floor craft” …). You can join this class at the beginning of each month.","Monday 18h-19h15,Wednesday 19h15-20h30");

INSERT INTO courses (id, name, description, schedule) VALUES 
(2, "Lindy foundations","You’ve taken the time to get acquainted with partner dancing and you’ve learned a few basic steps. We now invite you to deepen your vocabulary by getting to know the Lindy Hop, the mother of all swing dances!","Monday 19h15-20h30"),
(3, "Open Lindy", "Once the basics are learned in the Foundations class, Open Lindy is the class where we all come together, no matter how many years of experience we have. Improvisation, listening to music and partnering continue to be at the heart of our practice. We rely on the autonomy of each dancer to better guide you in your evolution. Learning is like a spiral: at each level of understanding, we are able to visit and revisit known subjects with a new perspective, and thus access a greater complexity. 

The dance floor is made up of many points of view and life trajectories. This course will be the best environment to practice communication, negotiation, listening and adaptation. It will allow us to practice this dance and this living together in its real practical context. 

You can join this class at any time and stay as long as you wish to be accompanied in your practice. The content will vary with the dancers present. 

Prerequisite: Lindy Foundations or equivalent ","Tuesday 18h-19h15"),
(4, "Advanced Lindy", "The title says it all. This course is here to challenge you. Whether it’s in the physical pace or the rigor of the existential questions you’ll be asked, you’ll be given plenty of work. Focus will be on improvisation, communication, sense of style, individual and reciprocal expression, the cultural modes of practice of jazz! 

You can join this class at any time and stay as long as you like. 

Prerequisite: Lindy Open","Thursday 19h15-20h30"),
(5, "Charleston open","The Charleston is one of the most famous dances of the jazz era and is a step that can be found in many dances that have since developed in the Black American communities, such as Locking, Chicago Footstep, and more. 
The lindy Hop incorporates a plethora of Charleston movements partnered and solo. This is an opportunity to work specifically on this energetic step that is very popular in faster tempos!  

You can join this class at any time and stay as long as you wish to be accompanied in your practice 

Prerequisite: Lindy Foundations or equivalent","Tuesday 19h15-20h30"),
(6, "Balboa foundations", "Have you dabbled in Balboa by taking an intro lesson? Are you looking to familiarize yourself with this exciting and subtle dance? Join us to learn to dance to faster swing music all night, explore fancy footwork and integrate close partnering. 

This class is for whether you are new or want a refresher of the foundations. 

You can join this class at any time and stay as long as you wish to be accompanied in your practice. Each month will focus on a balboa basic. This is the prerequisite for the Open Balboa class. Slippery Shoes recommended.","Tuesday 19h15-20h30"),
(7, "Balboa open","Once you’re comfortable with the 3 basics from the Balboa Foundations course, All-levels Balboa is where you can expand your knowledge and hone your craft through inspiration, improvisation and partnership. This course will be the best environment to practice expression, negotiation, listening and adaptation. 

You can join this class at any time and stay as long as you wish to be accompanied in your practice. Each month’s content will be different. Slippery Shoes recommended. 

Prerequisite: 3 – 6 months of Balboa Foundations","Tuesday 18h-19h15"),
(8, "Balboa advanced", "Gain refinement in your Balboa dancing. Increase your range and subtlety as you bring your skills to the next level. 

Audition required with the teachers. This class is for dancers with solid Balboa experience. 

You can join this class at any time and stay as long as you wish to be accompanied in your practice.","Thursday 18h-19h15"),
(9, "Blues Foundation","Have you been seduced by the different forms of Blues during an introductory lesson? Do you want to learn more about this dance with its many and varied qualities? Join us to learn how to dance the night away to blues music and become part of a community of enthusiasts. 

This course is aimed at dancers who are already familiar with swing and are looking to learn a new dance.","Wednesday 18h-19h15"),
(10, "Blues open","Blues dances vocabulary and expression. 
For those who have learned the basics and fundamentals of Blues dances. 

You can join this class at any time and stay as long as you wish to be accompanied in your practice.","Wednesday 19h15-20h30"),
(11, "Beginner tap","An introduction to the basics of tap dancing. You will learn the essential basic steps such as the toe, heel, shuffle, hop, flap … 

Spend a few weeks in this class to familiarize yourself with dancing and the sound of the shoes. A minimum of 4 weeks is the prerequisite to then integrate into the Tap 1 course.","Friday 18h-19h15");



CREATE TABLE contacts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    email_address VARCHAR(255) NOT NULL,
    message TEXT NOT NULL
);

INSERT INTO contacts (id, name, phone_number, email_address, message) VALUES 
(1, "Emily", "5145889888","em@gmail.com", "Nice Course");

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(255) NOT NULL UNIQUE,  
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL  
);


CREATE TABLE permissions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE user_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE role_permissions (
    role_id INT NOT NULL,
    permission_id INT NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES roles(id),
    FOREIGN KEY (permission_id) REFERENCES permissions(id)
);

CREATE TABLE course_user (
    course_id BIGINT NOT NULL,
    user_id INT NOT NULL,
    registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (course_id, user_id),
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS role_half_permissions;

CREATE TABLE role_half_permissions (
    role_id INT NOT NULL,
    permission_id INT NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES roles(id),
    FOREIGN KEY (permission_id) REFERENCES permissions(id)
);

