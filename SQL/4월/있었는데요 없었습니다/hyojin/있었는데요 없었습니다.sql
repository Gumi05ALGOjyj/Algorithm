select ins.animal_id, ins.name
from animal_ins ins left join animal_outs outs
using(animal_id)
where ins.datetime > outs.datetime
order by ins.datetime;

-- 외래키는 animal_id!
