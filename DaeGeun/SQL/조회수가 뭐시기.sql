select concat('/home/grep/src/',a.BOARD_ID,'/',FILE_ID,FILE_NAME,FILE_EXT)
from USED_GOODS_FILE a, (select BOARD_ID
                        from USED_GOODS_BOARD
                        order by VIEWS desc
                        limit 1) b
where a.BOARD_ID = b.BOARD_ID
order by a.FILE_ID desc
;
