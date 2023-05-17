select a.product_id, product_name, sum(price*amount)
from food_product a, (select product_id, amount
                    from food_order
                    where year(produce_date)=2022 and month(produce_date)=5) b
where a.product_id = b.product_id
group by product_cd
order by 3 desc, 1
