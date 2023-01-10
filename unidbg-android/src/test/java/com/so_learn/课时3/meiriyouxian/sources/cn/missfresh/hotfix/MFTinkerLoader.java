package cn.missfresh.hotfix;

import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import com.tencent.tinker.loader.AbstractTinkerLoader;
import com.tencent.tinker.loader.TinkerArkHotLoader;
import com.tencent.tinker.loader.TinkerDexLoader;
import com.tencent.tinker.loader.TinkerResourceLoader;
import com.tencent.tinker.loader.TinkerRuntimeException;
import com.tencent.tinker.loader.TinkerSoLoader;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.hotplug.ComponentHotplug;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import com.tencent.tinker.loader.shareutil.ShareSecurityCheck;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.File;

public class MFTinkerLoader extends AbstractTinkerLoader {
    private static final String TAG = "Tinker.MFTinkerLoader";
    private SharePatchInfo patchInfo;

    @Override // com.tencent.tinker.loader.AbstractTinkerLoader
    public Intent tryLoad(TinkerApplication tinkerApplication) {
        AppMethodBeat.i(4, false);
        Log.d(TAG, "tryLoad test test");
        Intent intent = new Intent();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        tryLoadPatchFilesInternal(tinkerApplication, intent);
        ShareIntentUtil.setIntentPatchCostTime(intent, SystemClock.elapsedRealtime() - elapsedRealtime);
        AppMethodBeat.o(4);
        return intent;
    }

    private void tryLoadPatchFilesInternal(TinkerApplication tinkerApplication, Intent intent) {
        ShareSecurityCheck shareSecurityCheck;
        File file;
        int i;
        int i2;
        AppMethodBeat.i(18, false);
        e.a(tinkerApplication);
        Log.w(TAG, "getMainUserPrivacyState\uff1a" + e.ad());
        if (e.ad() != 1) {
            Log.w(TAG, "tryLoadPatchFiles: tinker is disable, just return");
            ShareIntentUtil.setIntentReturnCode(intent, -1);
            AppMethodBeat.o(18);
            return;
        }
        int tinkerFlags = tinkerApplication.getTinkerFlags();
        if (!ShareTinkerInternals.isTinkerEnabled(tinkerFlags)) {
            Log.w(TAG, "tryLoadPatchFiles: tinker is disable, just return");
            ShareIntentUtil.setIntentReturnCode(intent, -1);
            AppMethodBeat.o(18);
        } else if (ShareTinkerInternals.isInPatchProcess(tinkerApplication)) {
            Log.w(TAG, "tryLoadPatchFiles: we don't load patch with :patch process itself, just return");
            ShareIntentUtil.setIntentReturnCode(intent, -1);
            AppMethodBeat.o(18);
        } else {
            File patchDirectory = SharePatchFileUtil.getPatchDirectory(tinkerApplication);
            if (patchDirectory == null) {
                Log.w(TAG, "tryLoadPatchFiles:getPatchDirectory == null");
                ShareIntentUtil.setIntentReturnCode(intent, -2);
                AppMethodBeat.o(18);
                return;
            }
            String absolutePath = patchDirectory.getAbsolutePath();
            if (!patchDirectory.exists()) {
                Log.w(TAG, "tryLoadPatchFiles:patch dir not exist:" + absolutePath);
                ShareIntentUtil.setIntentReturnCode(intent, -2);
                AppMethodBeat.o(18);
                return;
            }
            File patchInfoFile = SharePatchFileUtil.getPatchInfoFile(absolutePath);
            if (!patchInfoFile.exists()) {
                Log.w(TAG, "tryLoadPatchFiles:patch info not exist:" + patchInfoFile.getAbsolutePath());
                ShareIntentUtil.setIntentReturnCode(intent, -3);
                AppMethodBeat.o(18);
                return;
            }
            File patchInfoLockFile = SharePatchFileUtil.getPatchInfoLockFile(absolutePath);
            this.patchInfo = SharePatchInfo.readAndCheckPropertyWithLock(patchInfoFile, patchInfoLockFile);
            SharePatchInfo sharePatchInfo = this.patchInfo;
            if (sharePatchInfo == null) {
                ShareIntentUtil.setIntentReturnCode(intent, -4);
                AppMethodBeat.o(18);
                return;
            }
            boolean z = sharePatchInfo.isProtectedApp;
            intent.putExtra(ShareIntentUtil.INTENT_IS_PROTECTED_APP, z);
            String str = this.patchInfo.oldVersion;
            String str2 = this.patchInfo.newVersion;
            String str3 = this.patchInfo.oatDir;
            if (str == null || str2 == null || str3 == null) {
                Log.w(TAG, "tryLoadPatchFiles:onPatchInfoCorrupted");
                ShareIntentUtil.setIntentReturnCode(intent, -4);
                AppMethodBeat.o(18);
                return;
            }
            boolean isInMainProcess = ShareTinkerInternals.isInMainProcess(tinkerApplication);
            boolean z2 = this.patchInfo.isRemoveNewVersion;
            if (isInMainProcess && z2) {
                Log.w(TAG, "found clean patch mark and we are in main process, delete patch file now.");
                String patchVersionDirectory = SharePatchFileUtil.getPatchVersionDirectory(str2);
                if (patchVersionDirectory != null) {
                    boolean equals = str.equals(str2);
                    if (equals) {
                        str = "";
                    }
                    SharePatchInfo sharePatchInfo2 = this.patchInfo;
                    sharePatchInfo2.oldVersion = str;
                    sharePatchInfo2.newVersion = str;
                    sharePatchInfo2.isRemoveNewVersion = false;
                    SharePatchInfo.rewritePatchInfoFileWithLock(patchInfoFile, sharePatchInfo2, patchInfoLockFile);
                    SharePatchFileUtil.deleteDir(absolutePath + NotificationIconUtil.SPLIT_CHAR + patchVersionDirectory);
                    if (equals) {
                        ShareTinkerInternals.killProcessExceptMain(tinkerApplication);
                        ShareIntentUtil.setIntentReturnCode(intent, -2);
                        AppMethodBeat.o(18);
                        return;
                    }
                    str2 = str;
                }
            }
            intent.putExtra(ShareIntentUtil.INTENT_PATCH_OLD_VERSION, str);
            intent.putExtra(ShareIntentUtil.INTENT_PATCH_NEW_VERSION, str2);
            boolean z3 = !str.equals(str2);
            boolean equals2 = str3.equals(ShareConstants.CHANING_DEX_OPTIMIZE_PATH);
            String currentOatMode = ShareTinkerInternals.getCurrentOatMode(tinkerApplication, str3);
            intent.putExtra(ShareIntentUtil.INTENT_PATCH_OAT_DIR, currentOatMode);
            if (!z3 || !isInMainProcess) {
                str2 = str;
            }
            if (ShareTinkerInternals.isNullOrNil(str2)) {
                Log.w(TAG, "tryLoadPatchFiles:version is blank, wait main process to restart");
                ShareIntentUtil.setIntentReturnCode(intent, -5);
                AppMethodBeat.o(18);
                return;
            }
            String patchVersionDirectory2 = SharePatchFileUtil.getPatchVersionDirectory(str2);
            if (patchVersionDirectory2 == null) {
                Log.w(TAG, "tryLoadPatchFiles:patchName is null");
                ShareIntentUtil.setIntentReturnCode(intent, -6);
                AppMethodBeat.o(18);
                return;
            }
            String str4 = absolutePath + NotificationIconUtil.SPLIT_CHAR + patchVersionDirectory2;
            File file2 = new File(str4);
            if (!file2.exists()) {
                Log.w(TAG, "tryLoadPatchFiles:onPatchVersionDirectoryNotFound");
                ShareIntentUtil.setIntentReturnCode(intent, -6);
                AppMethodBeat.o(18);
                return;
            }
            String patchVersionFile = SharePatchFileUtil.getPatchVersionFile(str2);
            File file3 = patchVersionFile != null ? new File(file2.getAbsolutePath(), patchVersionFile) : null;
            if (!SharePatchFileUtil.isLegalFile(file3)) {
                Log.w(TAG, "tryLoadPatchFiles:onPatchVersionFileNotFound");
                ShareIntentUtil.setIntentReturnCode(intent, -7);
                AppMethodBeat.o(18);
                return;
            }
            ShareSecurityCheck shareSecurityCheck2 = new ShareSecurityCheck(tinkerApplication);
            int checkTinkerPackage = ShareTinkerInternals.checkTinkerPackage(tinkerApplication, tinkerFlags, file3, shareSecurityCheck2);
            if (checkTinkerPackage != 0) {
                Log.w(TAG, "tryLoadPatchFiles:checkTinkerPackage");
                intent.putExtra(ShareIntentUtil.INTENT_PATCH_PACKAGE_PATCH_CHECK, checkTinkerPackage);
                ShareIntentUtil.setIntentReturnCode(intent, -8);
                AppMethodBeat.o(18);
                return;
            }
            intent.putExtra(ShareIntentUtil.INTENT_PATCH_PACKAGE_CONFIG, shareSecurityCheck2.getPackagePropertiesIfPresent());
            boolean isTinkerEnabledForDex = ShareTinkerInternals.isTinkerEnabledForDex(tinkerFlags);
            boolean isArkHotRuning = ShareTinkerInternals.isArkHotRuning();
            if (isArkHotRuning || !isTinkerEnabledForDex || TinkerDexLoader.checkComplete(str4, shareSecurityCheck2, currentOatMode, intent)) {
                boolean isTinkerEnabledForArkHot = ShareTinkerInternals.isTinkerEnabledForArkHot(tinkerFlags);
                if (isArkHotRuning && isTinkerEnabledForArkHot && !TinkerArkHotLoader.checkComplete(str4, shareSecurityCheck2, intent)) {
                    Log.w(TAG, "tryLoadPatchFiles:dex check fail");
                    AppMethodBeat.o(18);
                } else if (!ShareTinkerInternals.isTinkerEnabledForNativeLib(tinkerFlags) || TinkerSoLoader.checkComplete(str4, shareSecurityCheck2, intent)) {
                    boolean isTinkerEnabledForResource = ShareTinkerInternals.isTinkerEnabledForResource(tinkerFlags);
                    Log.w(TAG, "tryLoadPatchFiles:isEnabledForResource:" + isTinkerEnabledForResource);
                    if (!isTinkerEnabledForResource || TinkerResourceLoader.checkComplete(tinkerApplication, str4, shareSecurityCheck2, intent)) {
                        boolean z4 = ShareTinkerInternals.isVmArt() && ShareTinkerInternals.isSystemOTA(this.patchInfo.fingerPrint) && Build.VERSION.SDK_INT >= 21 && !ShareTinkerInternals.isAfterAndroidO();
                        intent.putExtra(ShareIntentUtil.INTENT_PATCH_SYSTEM_OTA, z4);
                        if (isInMainProcess) {
                            if (z3) {
                                this.patchInfo.oldVersion = str2;
                            }
                            if (equals2) {
                                this.patchInfo.oatDir = currentOatMode;
                                Log.i(TAG, "tryLoadPatchFiles:oatModeChanged, try to delete interpret optimize files");
                                SharePatchFileUtil.deleteDir(str4 + NotificationIconUtil.SPLIT_CHAR + ShareConstants.INTERPRET_DEX_OPTIMIZE_PATH);
                            }
                        }
                        if (!checkSafeModeCount(tinkerApplication)) {
                            intent.putExtra(ShareIntentUtil.INTENT_PATCH_EXCEPTION, new TinkerRuntimeException("checkSafeModeCount fail"));
                            ShareIntentUtil.setIntentReturnCode(intent, -25);
                            Log.w(TAG, "tryLoadPatchFiles:checkSafeModeCount fail");
                            AppMethodBeat.o(18);
                            return;
                        }
                        if (isArkHotRuning || !isTinkerEnabledForDex) {
                            shareSecurityCheck = shareSecurityCheck2;
                            file = patchInfoFile;
                            i = 18;
                        } else {
                            shareSecurityCheck = shareSecurityCheck2;
                            boolean loadTinkerJars = TinkerDexLoader.loadTinkerJars(tinkerApplication, str4, currentOatMode, intent, z4, z);
                            if (z4) {
                                this.patchInfo.fingerPrint = Build.FINGERPRINT;
                                this.patchInfo.oatDir = loadTinkerJars ? ShareConstants.INTERPRET_DEX_OPTIMIZE_PATH : "odex";
                                file = patchInfoFile;
                                if (!SharePatchInfo.rewritePatchInfoFileWithLock(file, this.patchInfo, patchInfoLockFile)) {
                                    ShareIntentUtil.setIntentReturnCode(intent, -19);
                                    Log.w(TAG, "tryLoadPatchFiles:onReWritePatchInfoCorrupted");
                                    AppMethodBeat.o(18);
                                    return;
                                }
                                i = 18;
                                intent.putExtra(ShareIntentUtil.INTENT_PATCH_OAT_DIR, this.patchInfo.oatDir);
                            } else {
                                file = patchInfoFile;
                                i = 18;
                            }
                            if (!loadTinkerJars) {
                                Log.w(TAG, "tryLoadPatchFiles:onPatchLoadDexesFail");
                                AppMethodBeat.o(i);
                                return;
                            }
                        }
                        if (isArkHotRuning && isTinkerEnabledForArkHot && !TinkerArkHotLoader.loadTinkerArkHot(tinkerApplication, str4, intent)) {
                            Log.w(TAG, "tryLoadPatchFiles:onPatchLoadArkApkFail");
                            AppMethodBeat.o(i);
                        } else if (!isTinkerEnabledForResource || TinkerResourceLoader.loadTinkerResources(tinkerApplication, str4, intent)) {
                            if ((isTinkerEnabledForDex || isTinkerEnabledForArkHot) && isTinkerEnabledForResource) {
                                ComponentHotplug.install(tinkerApplication, shareSecurityCheck);
                            }
                            if (!isInMainProcess || !z3) {
                                i2 = 18;
                            } else if (!SharePatchInfo.rewritePatchInfoFileWithLock(file, this.patchInfo, patchInfoLockFile)) {
                                ShareIntentUtil.setIntentReturnCode(intent, -19);
                                Log.w(TAG, "tryLoadPatchFiles:onReWritePatchInfoCorrupted");
                                AppMethodBeat.o(18);
                                return;
                            } else {
                                i2 = 18;
                                ShareTinkerInternals.killProcessExceptMain(tinkerApplication);
                            }
                            ShareIntentUtil.setIntentReturnCode(intent, 0);
                            Log.i(TAG, "tryLoadPatchFiles: load end, ok!");
                            AppMethodBeat.o(i2);
                        } else {
                            Log.w(TAG, "tryLoadPatchFiles:onPatchLoadResourcesFail");
                            AppMethodBeat.o(i);
                        }
                    } else {
                        Log.w(TAG, "tryLoadPatchFiles:resource check fail");
                        AppMethodBeat.o(18);
                    }
                } else {
                    Log.w(TAG, "tryLoadPatchFiles:native lib check fail");
                    AppMethodBeat.o(18);
                }
            } else {
                Log.w(TAG, "tryLoadPatchFiles:dex check fail");
                AppMethodBeat.o(18);
            }
        }
    }

    private boolean checkSafeModeCount(TinkerApplication tinkerApplication) {
        AppMethodBeat.i(25, false);
        int safeModeCount = ShareTinkerInternals.getSafeModeCount(tinkerApplication);
        if (safeModeCount >= 2) {
            ShareTinkerInternals.setSafeModeCount(tinkerApplication, 0);
            AppMethodBeat.o(25);
            return false;
        }
        tinkerApplication.setUseSafeMode(true);
        ShareTinkerInternals.setSafeModeCount(tinkerApplication, safeModeCount + 1);
        AppMethodBeat.o(25);
        return true;
    }
}
