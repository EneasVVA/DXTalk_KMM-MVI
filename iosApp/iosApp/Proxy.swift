//
//  Proxy.swift
//  iosApp
//
//  Created by Eneas on 29/03/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation

protocol Proxy {
    func attach()
    func detach()
    func observe()
}
