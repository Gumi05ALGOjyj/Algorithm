select MONTH(START_DATE) as MONTH, CAR_ID as CAR_ID, count(HISTORY_ID) as RECORDS
from 
(select * from CAR_RENTAL_COMPANY_RENTAL_HISTORY group by car_id having start_date between '2022-08-01' and
    '2022-10-31' and count(*)>4) tmp
group by car_id,MONTH
order by MONTH, CAR_ID desc;
