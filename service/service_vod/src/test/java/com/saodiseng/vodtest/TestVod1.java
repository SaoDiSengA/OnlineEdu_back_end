package com.saodiseng.vodtest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import static com.saodiseng.vodtest.InitObject.initVodClient;

/**
 * ������Ƶid��ȡ��Ƶ����ƾ֤
 * 1��ʼ������
 * 2������ȡ��Ƶƾ֤request��response
 * 3��request������������Ƶid
 * 4���ó�ʼ����������ķ���������request����ȡ����
 */

public class TestVod1 {
    public static GetVideoPlayAuthResponse getVideoPlayAuth(DefaultAcsClient client) throws Exception {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId("3ece8475ffa04a58a60fffc19e14006e");
        return client.getAcsResponse(request);
    }

    /*����Ϊ����ʾ��*/
    public static void main(String[] argv) throws ClientException {
        DefaultAcsClient client = initVodClient("", "");
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        try {
            response = getVideoPlayAuth(client);
            //����ƾ֤
            System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
            //VideoMeta��Ϣ
            System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }
}
