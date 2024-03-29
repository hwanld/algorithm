select DATE_FORMAT(SALES_DATE, "%Y-%m-%d") as SALES_DATE, PRODUCT_ID, USER_ID, SALES_AMOUNT
from ONLINE_SALE
where MONTH(SALES_DATE) = 3 and YEAR(SALES_DATE) = 2022
union
select DATE_FORMAT(SALES_DATE, "%Y-%m-%d") as SALES_DATE, PRODUCT_ID, NULL as USER_ID, SALES_AMOUNT
from OFFLINE_SALE
where MONTH(SALES_DATE) = 3 and YEAR(SALES_DATE) = 2022
order by SALES_DATE asc, PRODUCT_ID asc, USER_ID asc;


