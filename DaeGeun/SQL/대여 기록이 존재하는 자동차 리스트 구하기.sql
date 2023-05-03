select distinct a.car_id
from car_rental_company_car a, (select car_id
                            from car_rental_company_rental_history
                            where month(start_date) = 10) b
where a.car_id = b.car_id
    and a.car_type = "세단"
order by 1 desc
;
