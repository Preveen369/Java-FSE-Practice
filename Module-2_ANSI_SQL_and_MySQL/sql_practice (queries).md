# SQL Practice Queries

This README provides various SQL queries for the `sql_practice` database. Each section includes a description and the corresponding query.

---

## User Upcoming Events

**Description:**  
Show a list of all upcoming events a user is registered for in their city, sorted by date.

```sql
select 
    Users.full_name, 
    Events.title, 
    Events.city, 
    Events.description, 
    Events.start_date, 
    Events.end_date 
from Users 
JOIN Registrations ON Users.user_id = Registrations.user_id 
JOIN Events ON Registrations.event_id = Events.event_id 
where Events.status = 'upcoming'
  and Users.city = Events.city
order by Events.start_date ASC;
```

---

## Top Rated Events

**Description:**  
Identify events with the highest average rating, considering only those that have received at least 10 feedback submissions.

```sql
select 
    Events.event_id, 
    Events.title, 
    avg(Feedback.rating) as average_rating, 
    count(Feedback.feedback_id) as feedback_count
from Events 
JOIN Feedback ON Events.event_id = Feedback.event_id
group by Events.event_id, Events.title
having count(Feedback.feedback_id) >= 10
order by average_rating DESC;
```

---

## Inactive Users

**Description:**  
Retrieve users who have not registered for any events in the last 90 days.

```sql
SELECT 
    Users.user_id,
    Users.full_name,
    Users.email,
    Users.city,
    Users.registration_date
FROM Users 
LEFT JOIN Registrations ON Users.user_id = Registrations.user_id
GROUP BY 
    Users.user_id, Users.full_name, Users.email, Users.city, Users.registration_date
HAVING 
    MAX(Registrations.registration_date) IS NULL 
    OR MAX(Registrations.registration_date) < CURDATE() - INTERVAL 90 DAY;
```

---

## Peak Session Hours

**Description:**  
Count how many sessions are scheduled between 10 AM to 12 PM for each event.

```sql
select 
    Events.event_id, 
    Events.title, 
    count(Sessions.session_id) as Number_of_Sessions
from Events 
LEFT JOIN Sessions ON Events.event_id = Sessions.event_id
where time(Sessions.start_time) >= '10:00:00' 
  AND time(Sessions.end_time) < '12:00:00'
group by Events.event_id, Events.title;
```

---

## Most Active Cities

**Description:**  
List the top 5 cities with the highest number of distinct user registrations.

```sql
select 
    Users.city, 
    count(distinct Users.user_id) as total_registered_users
from Users 
JOIN Registrations ON Users.user_id = Registrations.user_id
group by Users.city
order by total_registered_users DESC
limit 5;
```

---

## Event Resource Summary

**Description:**  
Generate a report showing the number of resources (PDFs, images, links) uploaded for each event.

```sql
select 
    Events.event_id, 
    Events.title, 
    SUM(CASE WHEN Resources.resource_type = 'pdf' THEN 1 ELSE 0 END) AS pdf_count,
    SUM(CASE WHEN Resources.resource_type = 'image' THEN 1 ELSE 0 END) AS image_count,
    SUM(CASE WHEN Resources.resource_type = 'link' THEN 1 ELSE 0 END) AS link_count
from Events 
LEFT JOIN Resources ON Events.event_id = Resources.event_id
group by Events.event_id, Events.title;
```

---

## Low Feedback Alerts

**Description:**  
List all users who gave feedback with a rating less than 3, along with their comments and associated event names.

```sql
select 
    Users.user_id, 
    Users.full_name, 
    Events.event_id,
    Events.title AS event_title, 
    Feedback.rating,
    Feedback.comments, 
    Feedback.feedback_date
from Feedback 
JOIN Users ON Users.user_id = Feedback.user_id 
JOIN Events ON Feedback.event_id = Events.event_id 
where Feedback.rating < 3;
```

---

## Sessions per Upcoming Event

**Description:**  
Display all upcoming events with the count of sessions scheduled for them.

```sql
select 
    Events.event_id, 
    Events.title as event_title, 
    count(Sessions.session_id) as total_sessions
from Events 
LEFT JOIN Sessions ON Events.event_id = Sessions.event_id
where status = 'upcoming'
group by Events.event_id, Events.title;
```

---

## Organizer Event Summary

**Description:**  
For each event organizer, show the number of events created and their current status (upcoming, completed, cancelled).

```sql
SELECT 
    Users.user_id, 
    Users.full_name AS organizer_name,
    Events.status, 
    COUNT(Events.event_id) AS total_events
FROM Users 
JOIN Events ON Users.user_id = Events.organizer_id
GROUP BY Users.user_id, Users.full_name, Events.status
ORDER BY Users.user_id, Events.status;
```

---

## Events Without Sessions

**Description:**  
List all events that currently have no sessions scheduled under them.

```sql
select 
    Events.event_id, 
    Events.title as event_title, 
    Events.start_date, 
    Events.status
from Events 
LEFT JOIN Sessions ON Sessions.event_id = Events.event_id 
where Sessions.session_id is null;
```

## Feedback Gap

**Description:**  
Identify events that had registrations but received no feedback at all.

```sql
select Events.event_id, Events.title as event_title
from Events JOIN Registrations 
ON Events.event_id = Registrations.event_id
LEFT JOIN Feedback 
ON Registrations.event_id = Feedback.event_id
where Feedback.feedback_id is null
group by Events.event_id, Events.title;
```

---

## Event with Maximum Sessions

**Description:**  
List the event(s) with the highest number of sessions.

```sql
SELECT Sessions.event_id, Events.title AS event_title, 
       COUNT(Sessions.session_id) AS session_count
FROM Sessions JOIN Events 
ON Sessions.event_id = Events.event_id
GROUP BY Sessions.event_id, Events.title
HAVING COUNT(Sessions.session_id) = (
    SELECT MAX(session_count)
    FROM (
        SELECT COUNT(session_id) AS session_count
        FROM Sessions
        GROUP BY event_id
    ) AS session_counts
);
```

---

## Daily New User Count

**Description:**  
Find the number of users who registered each day in the last 7 days.

```sql
SELECT 
    registration_date,
    COUNT(user_id) AS new_user_count
FROM Users
WHERE registration_date >= CURDATE() - INTERVAL 7 DAY
GROUP BY registration_date
ORDER BY registration_date DESC;
```

---

## Average Session Duration per Event

**Description:**  
Compute the average duration (in minutes) of sessions in each event.

```sql
SELECT 
    event_id,
    ROUND(AVG(TIMESTAMPDIFF(MINUTE, start_time, end_time)), 2) AS avg_session_duration_minutes
FROM Sessions
GROUP BY event_id;
```

---

## Registration Trends

**Description:**  
Show a month-wise registration count trend over the past 12 months.

```sql
SELECT 
    DATE_FORMAT(registration_date, '%Y-%m') AS year_month,
    COUNT(*) AS registrations_count
FROM Registrations
WHERE registration_date >= CURDATE() - INTERVAL 12 MONTH
GROUP BY year_month
ORDER BY year_month;
```

---

## Duplicate Registrations Check

**Description:**  
Detect if a user has been registered more than once for the same event.

```sql
SELECT 
    user_id,
    event_id,
    COUNT(*) AS registration_count
FROM Registrations
GROUP BY user_id, event_id
HAVING COUNT(*) > 1;
```

---

## Top Feedback Providers

**Description:**  
List top 5 users who have submitted the most feedback entries.

```sql
SELECT 
    Users.user_id,
    Users.full_name,
    COUNT(Feedback.feedback_id) AS feedback_count
FROM Users 
JOIN Feedback ON Users.user_id = Feedback.user_id
GROUP BY Users.user_id, Users.full_name
ORDER BY feedback_count DESC
LIMIT 5;
```

---

## User Engagement Index

**Description:**  
For each user, calculate how many events they attended and how many feedback entries they submitted.

```sql
SELECT 
    Users.user_id,
    Users.full_name,
    COUNT(DISTINCT Registrations.event_id) AS events_attended,
    COUNT(Feedback.feedback_id) AS feedbacks_submitted
FROM Users 
LEFT JOIN Registrations ON Users.user_id = Registrations.user_id
LEFT JOIN Feedback ON Users.user_id = Feedback.user_id
GROUP BY Users.user_id, Users.full_name
ORDER BY events_attended DESC, feedbacks_submitted DESC;
```

---

## Completed Events with Feedback Summary

**Description:**  
For completed events, show total registrations and average feedback rating.

```sql
SELECT 
    Events.event_id, 
    Events.title AS event_title,
    COUNT(DISTINCT Registrations.registration_id) AS total_registrations,
    ROUND(AVG(Feedback.rating), 2) AS average_feedback_ratings
FROM Events 
LEFT JOIN Registrations ON Events.event_id = Registrations.event_id
LEFT JOIN Feedback ON Registrations.event_id = Feedback.event_id
WHERE Events.status = 'completed'
GROUP BY Events.event_id, Events.title;
```

---

## Resource Availability Check

**Description:**  
List all events that do not have any resources uploaded.

```sql
select Events.event_id, Events.title as event_title
from Events JOIN Registrations
ON Events.event_id = Registrations.event_id
where Registrations.registration_id is null;
```

---

## Multi-Session Speakers

**Description:**  
Identify speakers who are handling more than one session across all events.

```sql
select speaker_name, count(*) as sessions_count
from Sessions group by speaker_name
having count(*) = 1
order by sessions_count desc;
```

---

## Unregistered Active Users

**Description:**  
Find users who created an account in the last 30 days but haven’t registered for any events.

```sql
SELECT 
    Users.user_id,
    Users.full_name,
    Users.email,
    Users.registration_date
FROM 
    Users 
LEFT JOIN 
    Registrations  ON Users.user_id = Registrations.user_id
WHERE 
    Users.registration_date >= CURDATE() - INTERVAL 30 DAY
    AND Registrations.user_id IS NULL;
```


---

## Event Session Time Conflict

**Description:**  
Identify overlapping sessions within the same event.

```sql
SELECT 
    s1.event_id,
    s1.title AS session_1,
    s2.title AS session_2,
    s1.start_time AS s1_start,
    s1.end_time AS s1_end,
    s2.start_time AS s2_start,
    s2.end_time AS s2_end
FROM 
    Sessions s1
JOIN 
    Sessions s2 
    ON s1.event_id = s2.event_id 
    AND s1.session_id < s2.session_id
WHERE 
    s1.start_time < s2.end_time 
    AND s2.start_time < s1.end_time;
```



---

## Most Registered Events 

**Description:**  
List top 3 events based on the total number of user registrations.

```sql
SELECT 
    Events.event_id,
    Events.title AS event_title,
    COUNT(Registrations.registration_id) AS total_registrations
FROM 
    Events
JOIN 
    Registrations ON Events.event_id = Registrations.event_id
GROUP BY 
    Events.event_id, Events.title
ORDER BY 
    total_registrations DESC
LIMIT 3;
```



---

## Average Rating per City

**Description:**  
Calculate the average feedback rating of events conducted in each city.

```sql
SELECT 
    Events.city,
    ROUND(AVG(Feedback.rating), 2) AS average_rating
FROM 
    Feedback 
JOIN 
    Events ON Feedback.event_id = Events.event_id
GROUP BY 
    Events.city
ORDER BY 
    average_rating DESC;
```


---

This document provides a clear overview of the SQL queries used to analyze various aspects of the `sql_practice` database.
