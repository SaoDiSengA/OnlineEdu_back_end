package com.saodiseng.vodtest;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;

public class TestVodUpload {
    static String accessKeyId = "";
    static String accessKeySecret = "";
    static String title = "hahahahahaahahaha"; //�ϴ�����ļ�����
    static String fileName = "C:\\Users\\13008\\Desktop\\6 - What If I Want to Move Faster.mp4"; //�����ļ���·������

    public static void main(String[] args) {
        testUploadVideo(accessKeyId,accessKeySecret,title,fileName);
    }
    private static void testUploadVideo(String accessKeyId, String accessKeySecret, String title, String fileName) {
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* ��ָ����Ƭ�ϴ�ʱÿ����Ƭ�Ĵ�С��Ĭ��Ϊ2M�ֽ� */
        request.setPartSize(2 * 1024 * 1024L);
        /* ��ָ����Ƭ�ϴ�ʱ�Ĳ����߳�����Ĭ��Ϊ1��(ע�������û�ռ�÷�����CPU��Դ������ݷ��������ָ����*/
        request.setTaskNum(1);
        /* �Ƿ����ϵ�����, Ĭ�϶ϵ��������ܹرա������粻�ȶ����߳������ʱ���ٴη�����ͬ�ϴ����󣬿��Լ���δ��ɵ��ϴ����������ڳ�ʱ3000���Բ����ϴ���ɵĴ��ļ���
        ע��: �ϵ����������󣬻����ϴ������н��ϴ�λ��д�뱾�ش����ļ���Ӱ���ļ��ϴ��ٶȣ���������ʵ�����ѡ���Ƿ���*/
        //request.setEnableCheckpoint(false);
        /* OSS��������־��ӡ��ʱʱ�䣬��ָÿ����Ƭ�ϴ�ʱ�䳬������ֵʱ���ӡdebug��־����������δ���־�����������ֵ����λ: ���룬Ĭ��Ϊ300000����*/
        //request.setSlowRequestsThreshold(300000L);
        /* ��ָ��ÿ����Ƭ������ʱ��ӡ��־��ʱ����ֵ��Ĭ��Ϊ300s*/
        //request.setSlowRequestsThreshold(300000L);
        /* �Ƿ���ʾˮӡ(��ѡ)��ָ��ģ����IDʱ������ģ��������ȷ���Ƿ���ʾˮӡ*/
        //request.setIsShowWaterMark(true);
        /* �����ϴ���ɺ�Ļص�URL(��ѡ)��������ͨ���㲥����̨�����¼�֪ͨ���μ��ĵ� https://help.aliyun.com/document_detail/55627.html */
        //request.setCallback("http://callback.sample.com");
        /* �Զ�����Ϣ�ص�����(��ѡ)������˵���ο��ĵ� https://help.aliyun.com/document_detail/86952.html#UserData */
        // request.setUserData("{\"Extend\":{\"test\":\"www\",\"localId\":\"xxxx\"},\"MessageCallback\":{\"CallbackURL\":\"http://test.test.com\"}}");
        /* ��Ƶ����ID(��ѡ) */
        //request.setCateId(0);
        /* ��Ƶ��ǩ,����ö��ŷָ�(��ѡ) */
        //request.setTags("��ǩ1,��ǩ2");
        /* ��Ƶ����(��ѡ) */
        //request.setDescription("��Ƶ����");
        /* ����ͼƬ(��ѡ) */
        //request.setCoverURL("http://cover.sample.com/sample.jpg");
        /* ģ����ID(��ѡ) */
        //request.setTemplateGroupId("8c4792cbc8694e7084fd5330e56a33d");
        /* ������ID(��ѡ) */
        //request.setWorkflowId("d4430d07361f0*be1339577859b0177b");
        /* �洢����(��ѡ) */
        //request.setStorageLocation("in-201703232118266-5sejdln9o.oss-cn-shanghai.aliyuncs.com");
        /* ����Ĭ���ϴ����Ȼص� */
        //request.setPrintProgress(false);
        /* �����Զ����ϴ����Ȼص� (����̳� VoDProgressListener) */
        //request.setProgressListener(new PutObjectProgressListener());
        /* ������ʵ�ֵ�����STS��Ϣ�Ľӿ�ʵ����*/
        // request.setVoDRefreshSTSTokenListener(new RefreshSTSTokenImpl());
        /* ����Ӧ��ID*/
        //request.setAppId("app-1000000");
        /* �㲥�������� */
        //request.setApiRegionId("cn-shanghai");
        /* ECS��������*/
        // request.setEcsRegionId("cn-shanghai");
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //������Ƶ�㲥���������ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* ������ûص�URL��Ч����Ӱ����Ƶ�ϴ������Է���VideoIdͬʱ�᷵�ش����롣��������ϴ�ʧ��ʱ��VideoIdΪ�գ���ʱ��Ҫ���ݷ��ش���������������ԭ�� */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }
}
