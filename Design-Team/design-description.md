# Requirements

This document describes how the different requirements were addressed in the current team design. To this 
end, each of the different requirements is subsequently "quoted" and followed by a description of how/what components 
of the app will be fulfilling this requirement.

 >1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit 
    current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet ).<br/>

- To realize this requirement, we implemented a "User" class. It serves as the main entry of the app. It has fours 
  methods that cover the aforementioned requirements. These different methods guide the user to the location in the 
  app that is used to fulfill the action he/she selects. The methods in the "User" class are:
  - enterCurrentJob
  - enterJobOffer
  - itemComparison
  - comparisonSettings
- The App GUI will handle showing these different functionalities once the app is turned on

<br/>

---

 >2. When choosing to enter current job details, a user will:<br/>
   a.	Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:<br/>
        i.	Title<br/>
        ii.	Company<br/>
        iii.	Location (entered as city and state)<br/>
        iv.	Cost of living in the location (expressed as an index)<br/>
        v.	Yearly salary<br/>
        vi.	Yearly bonus<br/>
        vii.	Allowed weekly telework days (expressed as the number of days per week allowed for remote work, inclusively between 0 and 5)<br/>
        viii.	Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)<br/>
        ix.	Number of company shares offered at hiring (valued at $1 per share and expressed as a number >= 0) <br/>

Two classes that allows the user to enter all aforementioned inputs:
"ItemInfo" which are:
 * DisplayCurrentJob --> That is used if the user is entering the info of the current job
 * DisplayJobOffer --> If the user is entering info about an offer. This Class allows the user to either compare it with
   the job by calling class "DisplayScoringAndRanking"

<br/>

   ---


> b.	Be able to either save the job details or cancel and exit without saving, returning in both cases to the 
main menu.<br/>

 The "DisplayCurrentJob" and "DisplayJobOffer" Classes include
 methods named:
 * save()
 * cancel()

The GUI can be designed such that there are buttons corresponding to these functionalities (methods). "ItemInfo" is 
a generalization of "CurrentJob" and "JobOffer"

<br/>

--- 

>3. When choosing to enter job offers, a user will:<br/>
   a.	Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.<br/>
   b.	Be able to either save the job offer details or cancel.<br/>
   c.	Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).<br/>


"DisplayJobOffer" contains a method to enter another offer once the current one is done
["EnterAnotherOffer()"] and allows for a quick comparison between that offer and the current job
by calling "DisplayScoringAndRanking"

The attributes associated with the job offers are input as part of the main Class "ItemInfo". "ItemInfo" is a generalization of "CurrentJob" and "JobOffer"

<br/>

---
>4.	When adjusting the comparison settings, the user can assign integer weights to:<br/>
      a.	Yearly salary<br/>
      b.	Yearly bonus<br/>
      c.	Allowed weekly telework days<br/>
      d.	Leave time<br/>
      e.	Shares offered<br/>
      If no weights are assigned, all factors are considered equal.<br/>


  Class "DisplayComparisonSettings" can be used to enter the weights for the different attributes.
  In the code, the default for these weights are set to 1.0

<br/>

---
>5.	When choosing to compare job offers, a user will:<br/>
a.	Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details),
       > and including the current job (if present), clearly indicated.
b.	Select two jobs to compare and trigger the comparison.<br/>
c.	Be shown a table comparing the two jobs, displaying, for each job:<br/>
i.	Title<br/>
ii.	Company<br/>
iii.	Location<br/>
iv.	Yearly salary adjusted for cost of living<br/>
v.	Yearly bonus adjusted for cost of living<br/>
vi.	Allowed weekly telework days<br/>
vii.	Leave time<br/>
viii.	Number of shares offered<br/>
d.	Be offered to perform another comparison or go back to the main menu.<br/>

 - The class "DisplayScoringAndRanking"  calculates the score of the selected offers and
   rank them. "DisplayCompareItems" shows a comparison between two offers or offer and current job side-by-side.
 - The "attributes" to "ItemComparison" are strings representing the "offers" and/or "current job" that need to be compared

<br/>

---
> 6.	When ranking jobs, a job’s score is computed as the weighted sum of:<br/>
>AYS + AYB + CSO/4 + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8)<br/>
>
>where:<br/>
AYS = yearly salary adjusted for cost of living<br/>
AYB = yearly bonus adjusted for cost of living<br/>
CSO = Company shares offered (assuming a 4-year vesting schedule and a price-per-share of $1)<br/>
LT = leave time<br/>
RWT = telework days per week<br/>
The rationale for the RWT subformula is:<br/>
a.	value of an employee hour = (AYS / 260) / 8<br/>
b.	commute hours per year (assuming a 1-hour/day commute) =<br/>
1 * (260 - 52 * RWT)<br/>
c.	therefore travel-time cost = (260 - 52 * RWT) * (AYS / 260) / 8<br/>
<br/>
For example, if the weights are 2 for the yearly salary, 2 for the shares offered, and 1 for all other factors, the 
> score would be computed as:<br/>
<br/>
2/7 * AYS + 1/7 * AYB + 2/7 * (CSO/4) + 1/7 * (LT * AYS / 260) - 1/7 * ((260 - 52 * RWT) * (AYS / 260) / 8)<br/>

 - Class "DisplayScoringAndRanking" calculates the score of each offer using the weights obtained from
"DisplayComparisonSettings" and the parameters from the SQL-lite database.

<br/>

---
> 7.	The user interface must be intuitive and responsive.<br/>


The interface will be made easy through the GUI generation