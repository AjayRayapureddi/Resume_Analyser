package com.project.resume;

import weka.classifiers.functions.Logistic;
import weka.core.Instances;

public class WekaFairLogisticTrainer {

    public static Logistic trainModel(Instances data) throws Exception {

        Logistic logistic = new Logistic();
        logistic.buildClassifier(data);

        return logistic;
    }

    public static void evaluate(Logistic model, Instances data) throws Exception {

        System.out.println("\n----- WEKA PREDICTIONS -----");

        for (int i = 0; i < data.numInstances(); i++) {
            double prediction = model.classifyInstance(data.instance(i));
            String predictedClass = data.classAttribute().value((int) prediction);

            System.out.println(
                "Instance " + (i + 1) + " → Predicted: " + predictedClass
            );
        }
    }
}
