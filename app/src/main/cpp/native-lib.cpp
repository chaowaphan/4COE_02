#include <jni.h>
#include <string>
#include <opencv2/core/core.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/features2d/features2d.hpp>

using namespace std;
using namespace cv;


extern "C"
{


    int toGray(Mat img, Mat gray){
        cvtColor(img,gray,CV_RGBA2GRAY);

        if (gray.rows == img.rows && gray.cols == img.rows){
            return 1;
        } else{
            return 0;
        }

    };


JNIEXPORT jint  JNICALL Java_com_example_chaow_apptest002_Opencamera2_ImgProcess(JNIEnv *env, jclass, jlong inputMat, jlong imageGray) {

    Mat& mRgb = *(Mat*) inputMat;
    Mat& mGray = *(Mat*) imageGray;

    int conv;
    jint retVal;
    conv = toGray(mRgb,mGray);
    retVal = (jint)conv;

    return retVal;
}



}
