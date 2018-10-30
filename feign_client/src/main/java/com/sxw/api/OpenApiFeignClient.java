/*
 * @(#)OpenApi.java 1.0 2018年8月4日
 * @Copyright: Copyright © 2007-2018 ky-express.com.All Rights Reserved.
 * @Description: Modification History:
 * @Date:        2018年8月4日
 * @Author:      lucius.lv
 * @Version:     1.0.0.0
 * @Description: (Initialize)
 * @Reviewer:
 * @Review Date:
 */
package com.sxw.api;

import com.kye.serviceaggregator.erp.model.LogisticsRouter;
import com.kye.serviceaggregator.erp.model.SubWaybill;
import com.kye.serviceaggregator.erp.request.*;
import com.kye.serviceaggregator.erp.response.*;
import com.kye.serviceaggregator.model.Token;
import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;
import java.util.Map;

@FeignClient(value = "openApiFeignClient", url = "${open.platform.url}", fallback = OpenApiFeignClientBreaker.class)
public interface OpenApiFeignClient {

    @Headers({"method: oms.preWaybillExternalPlatform.batchSave"})
    @RequestLine("POST /router/rest")
    ReturnObject<List<ErpWaybillRequest>> customerWaybillPrintBatch(List<ErpWaybillRequest> params);


    @Headers({"method: oms.preWaybillExternalPlatform.saveChild"})
    @RequestLine("POST /router/rest")
    ReturnObject<ErpObtainZDServiceResp> obtainZDService(Map<String, Object> params);


    @Headers({"method: oms.waybillExternalPlatform.getPicture"})
    @RequestLine("POST /router/rest")
    ReturnObject<List<String>> downloadFile_PHD(Map<String, Object> requestParameters);

    @Headers({"method: crm.ecResource.shopList"})
    @RequestLine("POST /router/rest")
    ReturnObject<List<SupplierInfo>> querySupplierInformation(Map<String, Object> requestParameters);


    @Headers({"method: oms.preWaybillExternalPlatform.batchGetWaybillNumber"})
    @RequestLine("POST /router/rest")
    ReturnObject<List<String>> batchGetWaybillNumber(Map<String, Object> requestParameters);


    @Headers({"method: pda.route.externalPlatform.getExteriorRoute"})
    @RequestLine("POST /router/rest")
    ReturnObject<List<LogisticsRouter>> getTWebYDTrackingV2(Map<String, Object> requestParameters);

    @Headers({"method: oms.preWaybillExternalPlatform.batchGetWaybillNumber"})
    @RequestLine("POST /router/rest")
    ReturnObject<List<String>> getLogisticsYDCode(Map<String, Object> requestParameters);


    @Headers({"method: crm.freightAmount.remote.get"})
    @RequestLine("POST /router/rest")
    ReturnObject<ErpCloudFreightCalculationResponse> cloudFreightCalculationInterface(ErpCloudFreightCalculationParams requestParameters);


    @Headers({"method: oms.preWaybillExternalPlatform.getWbnumAndChildnum"})
    @RequestLine("POST /router/rest")
    ReturnObject<SubWaybill> getLogisticsYDCodeByAreaCodeV2(Map<String, Object> requestParameters);


    @Headers({"method: oms.preWaybillExternalPlatform.deleteOrder"})
    @RequestLine("POST /router/rest")
    ReturnObject<Object> deleteOrder(Map<String, Object> requestParameters);


    @Headers({"method: oms.preWaybillExternalPlatform.updateOrder"})
    @RequestLine("POST /router/rest")
    ReturnObject<Object> updateOrder(ErpWaybillRequest requestParameters);


    @Headers({"method: oms.orderExternalPlatform.save"})
    @RequestLine("POST /router/rest")
    ReturnObject<Object> electronicPlatformXD_V2(ErpOmsOrderExternalPlatformSave postEntity);


    @Headers({"method: oms.preWaybillExternalPlatform.batchSaveOrder"})
    @RequestLine("POST /router/rest")
    ReturnObject<List<ErpResSaveOrder>> batchSaveOrder(List<ErpWaybillRequest> list);


    @RequestLine("POST /security/refreshToken")
    ReturnObject<Token> refreshToken(Map<String, String> params);

    @RequestLine("POST /security/accessToken")
    ReturnObject<Token> applyAccessToken(Map<String, String> params);

    @Headers({"method: oms.preWaybillExternalPlatform.save"})
    @RequestLine("POST /router/rest")
    ReturnObject<ErpWaybillRequest> customerWaybillPrintSave(ErpWaybillRequest requestParameters);


    @Headers({"method: oms.preWaybillExternalPlatform.saveOrder"})
    @RequestLine("POST /router/rest")
    ReturnObject<ErpWaybillRequest> customerWaybillPrintSaveOrder(ErpWaybillRequest requestParameters);

    @Headers({"method: oms.waybillExternalPlatform.searchWaybillPicture"})
    @RequestLine("POST /router/rest")
    ReturnObject<ErpPage<LogisticsWaybillPic>> searchWaybillPicture(Map<String, Object> requestParameters);


    @Headers({"method: oms.waybillExternalPlatform.search"})
    @RequestLine("POST /router/rest")
    ReturnObject<ErpPage<ErpWaybillFinance>> queryDesignatedCustomerWaybills(Map<String, Object> requestParameters);


    @Headers({"method: oms.preWaybillExternalPlatform.checkModifyDelete"})
    @RequestLine("POST /router/rest")
    ReturnObject<Boolean> deleteModifyOrderInformation(Map<String, Object> requestParameters);

    @Headers({"method: oms.preWaybillExternalPlatform.save"})
    @RequestLine("POST /router/rest")
    ReturnObject<ErpResSaveOrder> waybillInformationStorage(ErpWaybillRequest rep);


    @Headers({"method: oms.preWaybillExternalPlatform.batchSave"})
    @RequestLine("POST /router/rest")
    ReturnObject<List<ErpResSaveOrder>> waybillInfoPrint_Multi(List<ErpWaybillRequest> postList);


    /**
     *  查询客户资源单个的
     * @param requestParameters
     * @return
     */
    @Headers({"method: crm.ecResource.searchByShops"})
    @RequestLine("POST /router/rest")
    ReturnObject<ErpPage<ErpCustomer>> getCrmEcResourceSearchByShops(Map<String, Object> requestParameters);


    /**
     *  查询客户资源批量
     * @param requestParameters
     * @return
     */
    @Headers({"method: crm.inapp.customer.expansion.searchEcResourceByShopsIds"})
    @RequestLine("POST /router/rest")
    ReturnObject<ErpResCustomer> crmInappCustomerExpansionSearchEcResourceByShopsIds(Map<String, Object> requestParameters);



    @Headers({"method: KyDictApi.Dictionary.Fendan"})
    @RequestLine("POST /router/rest")
    ReturnObject<List<ResKyDictApiDictionaryFendan>> getKyDictApiDictionaryFendan(Map<String, Object> requestParameters);


    @Headers({"method: pda.route.externalPlatform.getExteriorRoute"})
    @RequestLine("POST /router/rest")
    ReturnObject<List<ErpResExteriorRoute>> getExteriorRoute(Map<String, Object> requestParameters);

    /**
     * 获得批量路由信息
     * @param requestParameters
     * @return
     */
    @Headers({"method: pda.route.externalPlatform.listExteriorRoute"})
    @RequestLine("POST /router/rest")
    ReturnObject<List<ErpResRouteBatch>> getlistExteriorRoute(Map<String, Object> requestParameters);




    @Headers({"method: tms.follow.externalPlatform.update"})
    @RequestLine("POST /router/rest")
    ReturnObject<ErpResPlatformUpdate> tmsFollowExternalPlatformUpdate(ErpReqPlatformUpdate parm);



    @Headers({"method: tms.follow.externalPlatform.listWaybillComposite"})
    @RequestLine("POST /router/rest")
    ReturnObject<List<ErpWaybillFinance>> tmsFollowExternalPlatformListWaybillComposite(Map<String, Object> requestParameters);

    
    @Headers({"method: tms.follow.externalPlatform.updateDeliverySign"})
    @RequestLine("POST /router/rest")
    ReturnObject<List<WaybillStateSyncResp>> updateDeliverySign(Map<String, Object> requestParam);

    
    @Headers({"method: tms.follow.externalPlatform.updatereceive"})
    @RequestLine("POST /router/rest")
    ReturnObject<List<WaybillStateSyncResp>> updatereceive(Map<String, Object> requestParam);
    
    @Headers({"method: baseconfig.pickupTakeArea.findByFiveAddress"})
    @RequestLine("POST /router/rest")
    ReturnObject<ErpResFindByFiveAddressData> baseconfigPickupTakeAreaFindByFiveAddress(ErpReqFindByFiveAddress requestParameters);

    
    @Headers({"method: KyDictApi.Dictionary.Fendan"})
    @RequestLine("POST /router/rest")
    ReturnObject<ErpResFendanData> KyDictApiDictionaryFendan(ErpReqFendan requestParameters);

    @Headers({"method: crm.inapp.customer.expansion.saveEcResource"})
    @RequestLine("POST /router/rest")
    ReturnObject<Object> saveEcResource(EcResourceParams requestParameter);


    @Headers({"method: crm.inapp.customer.expansion.cancelEcResource"})
    @RequestLine("POST /router/rest")
    ReturnObject<Object> cancelEcResource(Map<String, Object> requestParameter);
    
    
    @Headers({"method: hr.remoteEmployee.getByConpanyMobile"})
    @RequestLine("POST /router/rest")
    ReturnObject<ResGetByConpanyMobile> getByConpanyMobile(ReqGetByConpanyMobile requestParameter);

    @Headers({"method: tms.followfly.cloudWarehouse.listEffectiveInfo"})
    @RequestLine("POST /router/rest")
    ReturnObject<List<ErpResQueryLogisticsTakeTime>> queryLogisticsTakeTime(Map<String, Object> requestParameter);

    /**
     * 根据运单号查询跟飞信息-云仓对接
     */
    @Headers({"method: tms.followfly.cloudWarehouse.listCost"})
    @RequestLine("POST /router/rest")
    ReturnObject<List<ErpResTmsFollowflyCloudWarehouseListCost>> queryCloudWarehouseListCost(Map<String, Object> requestParameter);

    /**
     * 查询运单信息
     */
    @Headers({"method: tms.follow.cloudWarehouse.get"})
    @RequestLine("POST /router/rest")
    ReturnObject<ErpCloudWarehouseWaybillInfo> queryCloudWarehouseWaybillInfo(Map<String, Object> requestParameter);
}

