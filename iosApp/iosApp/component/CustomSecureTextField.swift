//
//  CustomSecureTextField.swift
//  iosApp
//
//  Created by Eneas on 27/03/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct CustomSecureTextField: View {
    var defaultTextField: String
    @Binding var stringTextField: String
    var callback: (String) -> Void?
    var secured: Bool = true
    
    var body: some View {
        SecureField(LocalizedStringKey(defaultTextField), text: $stringTextField)
        .onChange(of: self.stringTextField, perform: { newValue in
            self.callback(newValue)
        })
        .padding(15)
        .disableAutocorrection(true)
        .overlay(
          RoundedRectangle(cornerRadius: 10)
            .stroke(
              Color("lightGrayColor"), lineWidth: 1)
        )
        .overlay(
          HStack {
            if !self.stringTextField.isEmpty {
              Button(action: {}) {
                Image("ic_cancel_darker")
                  .resizable()
                  .frame(width: 18, height: 18)
                  .padding(.leading, 300)
                  .onTapGesture { self.stringTextField = "" }
              }
            }
          }
        )
        .frame(width: 350, height: 50, alignment: .leading)
    }
}

struct CustomSecureTextField_Previews: PreviewProvider {
    static var previews: some View {
        CustomSecureTextField(defaultTextField: "Password", stringTextField: Binding.constant("")) { String in
            return nil
        }
    }
}
