-- 코드를 입력하세요
SELECT product_id, product_name, product_cd, category, price
FROM food_product
WHERE PRICE = (
    SELECT MAX(price)
    FROM food_product
);