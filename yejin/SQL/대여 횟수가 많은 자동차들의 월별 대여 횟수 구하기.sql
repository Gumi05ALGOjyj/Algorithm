SELECT month(start_date), car_id, count(*) as records
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where month(start_date) between 8 and 10
group by month(start_date), car_id
having car_id in (
    select car_id
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    where month(start_date) between 8 and 10
    group by car_id
    having count(*) >= 5
)
order by month(start_date), car_id desc;
