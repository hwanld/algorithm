SELECT B.CATEGORY, SUM(BS.SALES) AS TOTAL_SALES
FROM BOOK AS B 
    JOIN BOOK_SALES AS BS 
        ON B.BOOK_ID = BS.BOOK_ID
WHERE YEAR(BS.SALES_DATE) = 2022 AND MONTH(BS.SALES_DATE) = 1
GROUP BY B.CATEGORY
ORDER BY B.CATEGORY