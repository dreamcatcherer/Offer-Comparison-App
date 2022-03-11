# Offer-Comparison-App

## Overview

The Job Offer Comparison App is a simple, single-user app to easily track and compare one's job offers based on an adjustable weighting system. This manual will demonstrate how to use the app and it will be divided into five sections: Main Menu, Entering Current Job Details, Entering Job Offers Details, Adjusting Comparison Settings, and Comparing Job Offers. 

## Application Usage

### APK

You can download and install the app apk from the ./APK/app-debug.apk

### Main Menu

Main Menu is the first screen a user will see when they start the app. Four buttons are shown in this menu: 

1. Enter or Edit Current Job
2. Enter Job Offers
3. Adjust Comparison Settings
4. Compare Job Offers

The functions of these buttons are straightforward, just as their names indicate.

### Entering Current Job Details

By clicking the `Enter or Edit Current Job` button on the Main Menu,  the user will be lead to a new screen to enter or edit their current job information.  All ten fields listed below need to be filled.

1. Job Title
2. Company
3. City (of the company)
4. State (of the company) 
5. Cost of living (Please check the index value on [Expatistan](https://www.expatistan.com/cost-of-living/city).)
6. Yearly salary
7. Yearly bonus
8. Weekly Telework (expressed as the number of days per week allowed for remote work, inclusively between 0 and 5)
9. Days PTO (vacation days and holiday and/or sick leave, as a single overall number of days)
10. Company Shares (Number of company shares offered at hiring, valued at $1 per share and expressed as a number >= 0)

At the bottom of the screen, there are two buttons for the user to choose.

- Save (the button is clickable only after all the required fields are filled in)
- Cancel

By clicking either the Save or Cancel button, the user will be lead to the Main Menu.

### Entering Job Offers Details

By clicking the `Enter Job Offers` button on the Main Menu,  the user will be lead to a new screen to enter their job offer information.  All ten fields listed below need to be filled.

1. Job Title
2. Company
3. City (of the company)
4. State (of the company) 
5. Cost of living (Please check the index value on [Expatistan](https://www.expatistan.com/cost-of-living/city).)
6. Yearly salary
7. Yearly bonus
8. Weekly Telework (expressed as the number of days per week allowed for remote work, inclusively between 0 and 5)
9. Days PTO (vacation days and holiday and/or sick leave, as a single overall number of days)
10. Company Shares (Number of company shares offered at hiring, valued at $1 per share and expressed as a number >= 0)

There are five buttons in two rows.

Two buttons are in the first row.

- Save (the button is clickable only after all the required fields are filled in)
- Cancel

In the second row, there are three options.

- Enter Another
- Main Menu
- Compare Offer

### Adjusting Comparison Settings

By clicking the `Adjust Comparison Settings` button on the Main Menu,  the user will be lead to a new screen to assign integer weights to five fields listed below.

1. Yearly salary
2. Yearly bonus
3. Allowed Telework
4. Leave Time
5. Shares Offered

By default, the values in these five fields all equal to 1. The user can change the values in any fields to any values that equal or greater than 0.

### Comparing Job Offers

By clicking the `Compare Job Offers` button on the Main Menu or the `Compare Offers` button on the Entering Job Offers Details screen,  the user will be lead to a new screen to compare two jobs.

To compare two jobs, the user need to select two lines of jobs and then hit the `COMPARE` button. A Job Comparison result screen will pop out, showing a table comparing the two jobs, displaying, for each job:

1. Job Title
2. Company
3. City (of the company)
4. State (of the company) 
5. Cost of living
6. Yearly salary
7. Yearly bonus
8. Weekly Telework
9. Days PTO
10. Company Shares

Two buttons are provided for the user below the result table. 

1. Back To Offer List (lead the user to the Compare Job Offers screen to pick another pair of jobs to compare)
2. Main Menu (return to Main Menu)
