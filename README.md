<img src="./readme/title1.svg"/>

<div align="center">

> Hello world! This is the project’s summary that describes the project plain and simple, limited to the space available.  

**[PROJECT PHILOSOPHY](https://github.com/huda-alzahabi/Safely#project-philosophy) • [WIREFRAMES](https://github.com/huda-alzahabi/Safely#wireframes) • [TECH STACK](https://github.com/huda-alzahabi/Safely#tech-stack) • [IMPLEMENTATION](https://github.com/huda-alzahabi/Safely#implementation) • [HOW TO RUN?](https://github.com/huda-alzahabi/Safely#how-to-run)**

</div>

<br><br>


<img id="project-philosophy" src="./readme/title2.svg"/>

> Safely is a medical app built to facilitate patients' lives. Safely is more than just a random medical app; it is multi language, multi theme , and it stores your medical records and directs you to the nearest hospitals, wherever you are.

### User Stories

#### &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  As a user: 

- I want to change the language of my app so that I can use it freely.
- I want to change the theme to dark so that I use it comfortably at night.


#### &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  As a patient: 

- I want to store my medical records so that I can access them whenever I want.
- I want to use Google Maps to locate me so that I can find the nearest hospitals.
- I want to be able to see the doctors' professions and years of experience so that I can book appoitments with them.

#### &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  As a doctor: 

- I want to be able to choose the hospital I work at, so that the patients can find me.
- I want to add my available time slots for appointments, so that the patients can access them.
- I want to receive notification about scheduled appointments, so that I become aware of the patient name and appointment date.

#### &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  As a hospital: 

- I want to add my location, my address, and outpatient clinics working hours, so the patient can find me.


<br><br>

<img id="wireframes" src="./readme/title3.svg"/>

> This design was planned before on paper, then moved to Figma app for the fine details.
Note that I didn't use any styling library or theme, all from scratch and using pure css and xml modules

| Welcome |  Login   | User Type  | Signup  |
| ------- |  ------- | -------    |-------  |
| ![Welcome](readme/figma_welcome.png)| ![Login](readme/figma_login.png) | ![User Type](readme/figma_usertype.png) | ![Signup](readme/figma_signup.png) |

Edit Profile | Location |  Hospitals   | Doctors  | 
| ------- | ------- |  ------- | -------    |
| ![Edit Profile](readme/edit_profile.png.png)| ![Location](readme/figma_location.png)| ![Hospitals](readme/figma_hospitals.png) | ![Doctors](readme/figma_doctors.png) | 


<br><br>

<img id="tech-stack" src="./readme/title4.svg"/>

Here's a brief high-level overview of the tech stack the Safely uses:

- This project uses the [Kotlin language](https://kotlinlang.org/docs/home.html). Kotlin is a cross-platform, statically typed, general-purpose programming language with type inference, announced by google as the preferred language for Android app developers.

- For persistent storage (database), the app uses [MongoDB](https://www.mongodb.com/atlas/database) to store the data and [Node.js](https://nodejs.org/en/) to serve the data.
- To send push notifications, the app uses [firebase](https://firebase.google.com/docs) which supports Android, iOS, and macOS.
- The app uses the fonts [Inter](https://fonts.google.com/specimen/Inter) and [Laila](https://fonts.google.com/?query=Laila) as its main fonts, and the design of the app adheres to the material design guidelines.



<br><br>
<img id="implementation" src="./readme/title5.svg"/>

> Using the above mentioned tech stacks and the wireframes built with figma from the user sotries we have, the implementation of the app is shown as below, these are screenshots and gifs from the real app

| Welcome/Login  | Signup Failed  | Patient Info/ Location| Medical Records
| -----------------| -----|-----|-----|
| ![Welcome/Login](readme/login_signup.gif) | ![Signup Failed](readme/email_in_use.gif) | ![Patient Info/ Location](readme/patient_info.gif) | ![Medical Records](readme/medical_records.gif) |

Patient Home/ Nearby Hospitals| Doctors  | Choose Day/Time   | Confirm Appointment|
| -----------------| -----|-----|-----|
![Patient Home/ Nearby Hospitals](readme/patient.gif)| ![Doctors](readme/doctor.gif) | ![Choose Day/Time](readme/date_time.gif) | ![Confirm Appointment](readme/confirm_appointment.gif) |

Doctor Availability| Upcoming Appointments  | Hospital Home  | Dark Mode
| -----------------| -----|-----|-----|
![Doctor Availability](readme/availability.gif)| ![Upcoming Appointments](readme/appointments.gif) | ![Hospital Home](readme/hospital.jpg) | ![Dark Mode](readme/dark.gif)

Safely in French| Patient Home |Doctor Info | Doctor Availability |
| -----------------| -----------------| -----------------| -----------------|
![Safely in French](readme/fr1.jpg)|![Safely in French](readme/fr2.jpg)|![Safely in French](readme/fr3.jpg)|![Safely in French](readme/fr4.jpg)

Admin|
| -----------------|
![Admin](readme/admin.gif)

<br><br>
<img id="how-to-run" src="./readme/title6.svg"/>


> To run the backend of the app, you need the following command:
      npm start    
> To run the frontend of the app, you need to open it in Android studio and click on the “Run” button, if you have an emulator, or plug an android device.

### Prerequisites

* npm
  ```sh
  npm install npm@latest -g
  ```

### Installation

1. Get a free API Key at [Google Maps Platform](https://developers.google.com/maps)
2. Clone the repo
   ```sh
   git clone https://github.com/huda-alzahabi/Safely.git
   ```
3. Install NPM packages
   ```sh
   npm install
   ```
4. Enter your API in `local.properties` in Frontend Folder 
   ```js
   MAPS_API_KEY = 'ENTER YOUR API';
   ```


