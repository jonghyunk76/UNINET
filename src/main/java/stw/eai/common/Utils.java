package stw.eai.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.siebel.data.SiebelPropertySet;

public class Utils {

	public synchronized static String copyFile(String srcPath, String dstPath, boolean flagSrcDelete)
			throws IOException {

		Logger logger = Utils.getLogger();

		// ���� ���
		File srcFile = new File(srcPath);
		File dstFile = new File(dstPath);

		BufferedInputStream  in  = null;
		BufferedOutputStream out = null;

		try {
			in  = new BufferedInputStream(new FileInputStream(srcFile));
			out = new BufferedOutputStream(new FileOutputStream(dstFile));

			byte[] buf = new byte[1024];

			int nRead = 0;

			while ((nRead = in.read(buf)) != -1) {
				out.write(buf, 0, nRead);
			}

			out.flush();

			logger.info("���� ���� ����");
		}
		finally {
			close(in);
			close(out);
		}

		if (flagSrcDelete) {
			boolean isDeleted = srcFile.delete();
			logger.info(String.format("��õ ���� ���� %s", isDeleted ? "����" : "����"));
		}

		return dstFile.toString();
	}

	public static void close(Closeable stream)
			throws IOException {

		if (stream != null) {
			stream.close();
		}
	}

	public static int getByteLength(String message, String charSet)
			throws UnsupportedEncodingException {

		Logger logger = Utils.getLogger();

		// �ݵ�� "EUC-KR"�� ����, �ѱ��� 2 Bytes�� ó���Ѵ�.
		// "UTF-8"������ �ѱ��� 3 Bytes�� ó���Ѵ�.
		byte[] bytes = message.getBytes(charSet);

		logger.debug(String.format("message=[%s]", message));
		logger.debug(String.format("����=[%d], Byte ��=[%d]", message.length(), bytes.length));

		return bytes.length;
	}

	public static Logger getLogger() {

		StackTraceElement[] arrayStack = Thread.currentThread().getStackTrace();

		String className = null;
		String loggerName = null;

		for (int i = arrayStack.length - 1; i >= 0; i--) {
			className = arrayStack[i].getClassName();

			if (className.startsWith("stw")) {
				loggerName = className.substring(0, className.lastIndexOf("."));
				break;
			}
		}
		
		return LoggerFactory.getLogger(loggerName);
	}

	public static String setNotNull(String field, String defaultValue) {

		return (field == null) ? defaultValue : field;
	}

	public static void setOutputPS(SiebelPropertySet output, String ERROR_CODE, String ERROR_MESSAGE) {


		output.setProperty("Error Code"   , ERROR_CODE   );
		output.setProperty("Error Message", ERROR_MESSAGE);
	}


	public static String getStackTrace(Throwable throwable) {

		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		throwable.printStackTrace(printWriter);

		return String.format("***** STACK TRACE *****%n%n%s", writer.toString());
	}



}
