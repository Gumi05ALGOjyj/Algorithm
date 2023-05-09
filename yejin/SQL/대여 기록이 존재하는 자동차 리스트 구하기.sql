select distinct c2.car_id 
from CAR_RENTAL_COMPANY_CAR as c1 inner join CAR_RENTAL_COMPANY_RENTAL_HISTORY as c2
on c1.car_id = c2.car_id and c1.car_type = '세단' and month(c2.start_date) = 10
order by c2.car_id desc;
