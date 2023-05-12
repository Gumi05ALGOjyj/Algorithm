select car_id, 
    case when car_id IN(
        select car_id from CAR_RENTAL_COMPANY_RENTAL_HISTORY where '2022-10-16' between date_format(start_date, '%Y-%m-%d')         and date_format(end_date, '%Y-%m-%d')
    )
    then '대여중'
    else '대여 가능'
    end
    as 'AVAILABILITY'
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
group by car_id
order by car_id desc;


=> 아래는 제가 실수한 코드인데 왜 아래 코드는 틀렸는지 모르겠어요 ㅠㅠ
# select car_id, 
#     case 
#     when '2022-10-16' between date_format(start_date, '%Y-%m-%d') and date_format(end_date, '%Y-%m-%d') then '대여중'
#     else '대여 가능'
#     end
#     as 'AVAILABILITY'
# from CAR_RENTAL_COMPANY_RENTAL_HISTORY
# group by car_id
# order by car_id desc;
