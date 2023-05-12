select p.product_id, p.product_name, sum(p.price * o.amount) as 'TOTAL_SALES'
from food_product p inner join food_order o on p.product_id = o.product_id
where date_format(o.produce_date, '%Y-%m') = '2022-05'
group by p.product_id
order by TOTAL_SALES desc, p.product_id asc;
