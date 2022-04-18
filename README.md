# renard
即時股市概況觀測網站

# 系統功能
- 取得即時大盤資訊
- 提供股市大盤走勢圖
- 取得即時個股資訊
- 查訊個股歷史資訊
- 查訊上市公司資訊
- 提供模擬交易系統
  - 紀錄交易紀錄
  - 計算交易盈虧
  - 計算存股市值

# 系統概略
![System overview]https://github.com/znkaihuang/renard/blob/main/System%20overview.drawio.png

renardClient: forntend，透過HttpRequest向renardServer取得資料<br>
renardServer: backend，從資料來源處取得所需資料，提供資料給renardClient<br>

## Screenshot

- Index plot
![IndexPlot.png](https://github.com/znkaihuang/renard/blob/main/renardScreenShot/IndexPlot.png)

- Index plot and index history table
![IndexPlotAndIndexHistoryTable.png](https://github.com/znkaihuang/renard/blob/main/renardScreenShot/IndexPlotAndIndexHistoryTable.png)

- Company index plot
![CompanyIndexPlot.png](https://github.com/znkaihuang/renard/blob/main/renardScreenShot/CompanyIndexPlot.png)

- Company index plot and index history table
![CompanyIndexPlotAndIndexHistoryTable.png](https://github.com/znkaihuang/renard/blob/main/renardScreenShot/CompanyIndexPlotAndIndexHistoryTable.png)

- Search company info
![SearchCompanyInfo.png](https://github.com/znkaihuang/renard/blob/main/renardScreenShot/SearchCompanyInfo.png)

- Order system
![OrderSystem1.png](https://github.com/znkaihuang/renard/blob/main/renardScreenShot/OrderSystem1.png)
![OrderSystem2.png](https://github.com/znkaihuang/renard/blob/main/renardScreenShot/OrderSystem2.png)

# 資料來源
臺灣證券交易所 基本市況報導網站
https://mis.twse.com.tw/stock/index.jsp

臺灣證券交易所 OpenAPI
https://openapi.twse.com.tw/

twstock 台灣股市股票價格擷取
https://github.com/mlouielu/twstock
