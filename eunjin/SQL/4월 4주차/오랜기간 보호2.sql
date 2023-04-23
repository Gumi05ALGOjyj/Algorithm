select outs.ANIMAL_ID,outs.NAME
from ANIMAL_OUTS outs, ANIMAL_INS ins
where outs.ANIMAL_ID = ins.ANIMAL_ID
order by datediff(outs.DATETIME, ins.DATETIME) DESC
limit 2;
